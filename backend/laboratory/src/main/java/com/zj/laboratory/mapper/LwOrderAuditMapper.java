package com.zj.laboratory.mapper;

import com.zj.laboratory.pojo.LwOrderAudit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 审核信息表(lw_order_audit)数据Mapper
 *
 * @author lbj
 * @since 2021-04-22 09:20:18
*/
@Component
public interface LwOrderAuditMapper {

    LwOrderAudit getByServiceNo(Long serviceNo);
}
