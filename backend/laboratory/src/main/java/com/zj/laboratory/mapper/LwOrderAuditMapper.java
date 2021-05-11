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

    /**
     * 根据订单编号查询
     * @param serviceNo
     * @return
     */
    LwOrderAudit getByServiceNo(String serviceNo);

    /**
     * 添加
     * @param lwOrderAudit
     */
    void save(LwOrderAudit lwOrderAudit);

    /**
     * 更新审核信息
     * @param lwOrderAudit
     */
    int update(LwOrderAudit lwOrderAudit);
}
