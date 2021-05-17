package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.*;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.pojo.vo.LwServiceOrderVo;
import com.zj.laboratory.pojo.vo.OrderMonthVo;
import com.zj.laboratory.pojo.vo.OrderPointVo;
import com.zj.laboratory.service.LwServiceOrderService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("serviceOrder")
public class LwServiceOrderController {
    @Autowired
    private LwServiceOrderService lwServiceOrderService;
    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<LwServiceOrderVo>> getByPage(@RequestBody Page<LwOrder> page) {
        Page<LwServiceOrderVo> result = lwServiceOrderService.getByPage(page);
        return new Result<>(result);
    }
    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<?> delete(@PathVariable Long id) {
        lwServiceOrderService.delete(id);
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
        lwServiceOrderService.enableById(id);
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
        lwServiceOrderService.disableById(id);
        return new Result<>("操作成功");
    }
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<LwServiceOrderVo> get(@PathVariable Long id) {
        LwServiceOrderVo lwServiceOrderVo = lwServiceOrderService.get(id);
        return new Result<>(lwServiceOrderVo);
    }
    /**
     * 添加
     *
     * @param lwOrder
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<?> save(@RequestBody LwOrder lwOrder) {
        lwServiceOrderService.save(lwOrder);
        return new Result<>("添加成功");
    }
    /**
     * 修改
     *
     * @param lwOrder
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<?> update(@RequestBody LwOrder lwOrder) {
        lwServiceOrderService.update(lwOrder);
        return new Result<>("修改成功");
    }

    /**
     * 获取列表
     * @param page
     * @return
     */
    @RequestMapping(value = "getListByPage",method = RequestMethod.POST)
    public Result<Page<LwServiceOrderVo>> getListByPage(@RequestBody Page<LwOrder> page){
        Page<LwServiceOrderVo> result=lwServiceOrderService.getListByPage(page);
        return new Result<>(result);
    }

    /**
     * 获取当前用户[总条数,审批中,已通过]
     * @return
     */
    @RequestMapping(value = "getCountList",method = RequestMethod.GET)
    public Result<LwUserStatistic> getCount(){
        LwUserStatistic count=lwServiceOrderService.getCountList();
        return new Result<>(count);
    }

    /**
     * 获取总数量
     * @return
     */
    @RequestMapping(value = "getServiceTotalCount",method = RequestMethod.GET)
    public Result<LwUserStatistic> getServiceTotalCount(){
        LwUserStatistic count=lwServiceOrderService.getServiceTotolCount();
        return new Result<>(count);
    }

    /**
     * 添加反馈
     * @param lwOrderFeedback
     * @return
     */
    @RequestMapping(value = "addFeedBack",method = RequestMethod.POST)
    public Result<LwUserStatistic> addFeedBack(@RequestBody LwOrderFeedback lwOrderFeedback){
        lwServiceOrderService.save(lwOrderFeedback);
        return new Result<>("反馈成功");
    }
    /**
     * 审核进场单
     * @return
     */
    @RequestMapping(value = "examine",method = RequestMethod.PUT)
    public Result<?> toExamine(@RequestBody LwOrderAudit lwOrderAudit){
        lwServiceOrderService.examine(lwOrderAudit);
        return new Result<>("审核成功!");
    }
    /**
     * 查询当月订单
     *
     * @return
     */
    @RequestMapping(value = "/monthOrder", method = RequestMethod.GET)
    public Result<List<OrderMonthVo>> monthOrder() {
        List<OrderMonthVo> list = lwServiceOrderService.monthOrder();
        return new Result<>(list);
    }
    /**
     * 查询每种订单的占比
     *
     * @return
     */
    @RequestMapping(value = "/orderPoint", method = RequestMethod.GET)
    public Result<List<OrderPointVo>> orderPoint() {
        List<OrderPointVo> list = lwServiceOrderService.orderPoint();
        return new Result<>(list);
    }
}
