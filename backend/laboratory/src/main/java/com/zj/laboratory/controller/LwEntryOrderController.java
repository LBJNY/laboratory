package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.*;
import com.zj.laboratory.pojo.dto.LwEntryOrderDto;
import com.zj.laboratory.pojo.vo.*;
import com.zj.laboratory.service.LwEntryOrderService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entryOrder")
public class LwEntryOrderController {
    @Autowired
    private LwEntryOrderService lwEntryOrderService;
    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<LwEntryOrderVo>> getByPage(@RequestBody Page<LwEntry> page) {
        Page<LwEntryOrderVo> result = lwEntryOrderService.getByPage(page);
        return new Result<>(result);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<LwEntryOrderVo> get(@PathVariable Long id) {
        LwEntryOrderVo lwEntryOrderVo = lwEntryOrderService.get(id);
        return new Result<>(lwEntryOrderVo);
    }
    /**
     * 根据id查询修改信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUpdateInfo/{id}", method = RequestMethod.GET)
    public Result<LwEntryOrderVo> getUpdateInfo(@PathVariable Long id) {
        LwEntryOrderVo lwEntryOrderVo = lwEntryOrderService.getUpdateInfo(id);
        return new Result<>(lwEntryOrderVo);
    }
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<?> delete(@PathVariable Long id) {
        lwEntryOrderService.delete(id);
        return new Result<>("删除成功");
    }
    /**
     * 有效
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enableById/{id}", method = RequestMethod.PUT)
    public Result<?> enableById(@PathVariable Long id) {
        lwEntryOrderService.enableById(id);
        return new Result<>("操作成功");
    }

    /**
     * 失效
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disableById/{id}", method = RequestMethod.PUT)
    public Result<?> disableById(@PathVariable Long id) {
        lwEntryOrderService.disableById(id);
        return new Result<>("操作成功");
    }
    /**
     * 添加
     *
     * @param lwEntry
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<?> save(@RequestBody LwEntry lwEntry) {
        lwEntryOrderService.save(lwEntry);
        return new Result<>("添加成功");
    }
    /**
     * 修改
     *
     * @param lwEntry
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<?> update(@RequestBody LwEntry lwEntry) {
        lwEntryOrderService.update(lwEntry);
        return new Result<>("修改成功");
    }

    /**
     * 获取当前用户[总条数,审批中,已通过]
     * @return
     */
    @RequestMapping(value = "getCountList",method = RequestMethod.GET)
    public Result<LwUserStatistic> getCountList(){
        LwUserStatistic list=lwEntryOrderService.getCountList();
        return new Result<>(list);
    }
    /**
     * 获取列表
     * @param page
     * @return
     */
    @RequestMapping(value = "getListByPage",method = RequestMethod.POST)
    public Result<Page<LwEntryOrderVo>> getListByPage(@RequestBody Page<LwEntry> page){
        Page<LwEntryOrderVo> result=lwEntryOrderService.getListByPage(page);
        return new Result<>(result);
    }

    /**
     * 获取进场单有关数量
     * @return
     */
    @RequestMapping(value = "getEntryTotalCount",method = RequestMethod.GET)
    public Result<LwUserStatistic> getEntryTotalCount(){
        LwUserStatistic count=lwEntryOrderService.getEntryTotolCount();
        return new Result<>(count);
    }

    /**
     * 审核进场单
     * @return
     */
    @RequestMapping(value = "examine",method = RequestMethod.PUT)
    public Result<?> toExamine(@RequestBody LwEntry lwEntry){
        lwEntryOrderService.examine(lwEntry);
        return new Result<>("审核成功!");
    }

    /**
     * 查询当月订单
     *
     * @return
     */
    @RequestMapping(value = "/monthOrder", method = RequestMethod.GET)
    public Result<List<OrderMonthVo>> monthOrder() {
        List<OrderMonthVo> list = lwEntryOrderService.monthOrder();
        return new Result<>(list);
    }
    /**
     * 查询每种订单的占比
     *
     * @return
     */
    @RequestMapping(value = "/orderPoint", method = RequestMethod.GET)
    public Result<List<OrderPointVo>> orderPoint() {
        List<OrderPointVo> list = lwEntryOrderService.orderPoint();
        return new Result<>(list);
    }
}
