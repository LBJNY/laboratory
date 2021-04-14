package com.zj.laboratory.controller;


import com.zj.laboratory.pojo.SysLog;
import com.zj.laboratory.pojo.dto.SysLogDto;
import com.zj.laboratory.service.SysLogService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 日志控制层
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Result<SysLog> get(@PathVariable Long id) {
        SysLog sysLog = sysLogService.get(id);
        return new Result<>(sysLog);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        sysLogService.delete(id);
        return new Result<>("删除成功");
    }

    /**
     * 分页查询
     *
     * @param sysLogDto
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<SysLog>> getByPage(@RequestBody SysLogDto sysLogDto) {
        Page<SysLog> page = sysLogService.getByPage(sysLogDto);
        return new Result<>(page);
    }

}