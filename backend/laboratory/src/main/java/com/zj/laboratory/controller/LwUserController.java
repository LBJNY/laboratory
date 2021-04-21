package com.zj.laboratory.controller;


import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.pojo.vo.LwUserStatisticVo;
import com.zj.laboratory.pojo.vo.LwUserVo;
import com.zj.laboratory.service.LwUserService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class LwUserController {

    @Autowired
    private LwUserService lwUserService;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<LwUser>> getByPage(@RequestBody Page<LwUser> page) {
        page = lwUserService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<LwUserVo> get(@PathVariable Long id) {
        LwUserVo lwUserVo = lwUserService.get(id);
        return new Result<>(lwUserVo);
    }
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUpdateInfo/{id}", method = RequestMethod.GET)
    public Result<LwUserVo> getUpdateInfo(@PathVariable Long id) {
        LwUserVo lwUserVo = lwUserService.getUpdateInfo(id);
        return new Result<>(lwUserVo);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<?> delete(@PathVariable Long id) {
        lwUserService.delete(id);
        return new Result<>("删除成功");
    }

    /**
     * 解封
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enableById/{id}", method = RequestMethod.PUT)
    public Result<?> enableById(@PathVariable Long id) {
        lwUserService.enableById(id);
        return new Result<>("操作成功");
    }

    /**
     * 封禁
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disableById/{id}", method = RequestMethod.PUT)
    public Result<?> disableById(@PathVariable Long id) {
        lwUserService.disableById(id);
        return new Result<>("操作成功");
    }

    /**
     * 更新用户权限-name
     * @param lwUser
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result<?> update(@RequestBody LwUser lwUser) {
        lwUserService.update(lwUser);
        return new Result<>("操作成功");
    }

    /**
     * 查询累计消费最多的前十个用户
     *
     * @return
     */
    @RequestMapping(value = "/getTopStatistic", method = RequestMethod.GET)
    public Result<List<LwUserStatisticVo>> getTopStatistic() {
        List<LwUserStatisticVo> list = lwUserService.getTopStatistic();
        return new Result<>(list);
    }
}