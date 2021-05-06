package com.zj.laboratory.controller;

import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.pojo.LwOrderServiceType;
import com.zj.laboratory.pojo.vo.LwOrderServiceTypeVo;
import com.zj.laboratory.service.LwOrderServiceTypeService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("serviceType")
public class LwOrderServiceTypeController {
    @Autowired
    private LwOrderServiceTypeService lwOrderServiceTypeService;
    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<LwOrderServiceType>> getByPage(@RequestBody Page<LwOrderServiceType> page) {
        page = lwOrderServiceTypeService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 更新信息
     *
     * @param lwOrderServiceType
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<?> update(@RequestBody LwOrderServiceType lwOrderServiceType) {
        lwOrderServiceTypeService.update(lwOrderServiceType);
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
        lwOrderServiceTypeService.enableById(Integer.parseInt(id));
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
        lwOrderServiceTypeService.disableById(Integer.parseInt(id));
        return new Result<>("操作成功");
    }

    /**
     * 添加
     *
     * @param lwOrderServiceType
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody LwOrderServiceType lwOrderServiceType) {
        lwOrderServiceTypeService.save(lwOrderServiceType);
        return new Result<>("操作成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public Result<?> delete(@PathVariable("id") String id) {
        lwOrderServiceTypeService.delete(Integer.parseInt(id));
        return new Result<>("删除成功");
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<LwOrderServiceType> get(@PathVariable("id") String id) {
        LwOrderServiceType lwOrderServiceType = lwOrderServiceTypeService.get(Integer.parseInt(id));
        return new Result<>(lwOrderServiceType);
    }
    /**
     * 获取所有
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result<List<LwOrderServiceTypeVo>> getAll() {
        List<LwOrderServiceTypeVo> list = lwOrderServiceTypeService.getAll();
        return new Result<>(list);
    }
}
