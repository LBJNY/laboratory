package com.zj.laboratory.service;


import com.zj.laboratory.pojo.SysLog;
import com.zj.laboratory.pojo.dto.SysLogDto;
import com.zj.laboratory.utils.Page;

/**
 */
public interface SysLogService {

    /**
     * 保存日志
     * @param logger
     */
    void save(SysLog logger);

    /**
     * 分页查询日志
     * @param sysLogDto
     * @return
     */
    Page<SysLog> getByPage(SysLogDto sysLogDto);

    /**
     * 根据id删除日志
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id获取日志
     * @param id
     * @return
     */
    SysLog get(Long id);
}