package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.LwEntry;
import com.zj.laboratory.pojo.LwOrder;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.pojo.vo.LwServiceOrderVo;
import com.zj.laboratory.service.LwServiceOrderService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
