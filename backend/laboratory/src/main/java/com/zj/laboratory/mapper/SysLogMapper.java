package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.SysLog;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public interface SysLogMapper {

    /**
     * 保存日志
     * @param logger
     */
    void save(SysLog logger);
}
