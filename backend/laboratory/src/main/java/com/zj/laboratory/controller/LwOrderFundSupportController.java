package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.service.LwOrderFundSupportService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fundSupport")
public class LwOrderFundSupportController {
    @Autowired
    private LwOrderFundSupportService lwOrderFundSupportService;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<LwOrderFundSupport>> getByPage(@RequestBody Page<LwOrderFundSupport> page) {
        page = lwOrderFundSupportService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 更新信息
     *
     * @param lwOrderFundSupport
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<?> update(@RequestBody LwOrderFundSupport lwOrderFundSupport) {
        lwOrderFundSupportService.update(lwOrderFundSupport);
        return new Result<>("操作成功");
    }

    /**
     * 解封
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enableById/{id}", method = RequestMethod.PUT)
    public Result<?> enableById(@PathVariable String id) {
        lwOrderFundSupportService.enableById(Integer.parseInt(id));
        return new Result<>("操作成功");
    }

    /**
     * 封禁
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disableById/{id}", method = RequestMethod.PUT)
    public Result<?> disableById(@PathVariable String id) {
        lwOrderFundSupportService.disableById(Integer.parseInt(id));
        return new Result<>("操作成功");
    }

    /**
     * 添加
     *
     * @param lwOrderFundSupport
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody LwOrderFundSupport lwOrderFundSupport) {
        lwOrderFundSupportService.save(lwOrderFundSupport);
        return new Result<>("操作成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<?> delete(@PathVariable("id") String id) {
        lwOrderFundSupportService.delete(Integer.parseInt(id));
        return new Result<>("删除成功");
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<LwOrderFundSupport> get(@PathVariable("id") String id) {
        LwOrderFundSupport lwOrderFundSupport = lwOrderFundSupportService.get(Integer.parseInt(id));
        return new Result<>(lwOrderFundSupport);
    }
    /**
     * 获取所有
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result<List<LwOrderFundSupport>> getAll() {
        List<LwOrderFundSupport> list = lwOrderFundSupportService.getAll();
        return new Result<>(list);
    }

}
