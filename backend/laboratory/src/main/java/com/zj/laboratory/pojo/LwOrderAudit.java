package com.zj.laboratory.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 审核信息表(lw_order_audit)实体类
 *
 * @author lbj
 * @since 2021-04-22 09:20:18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwOrderAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Long id;
    /**
     * 服务委托单编号
     */
    private String serviceNo;
    /**
     * 申请单位和部门审核意见（0：待审核；1：通过；2：未通过）
     */
    private Integer deptAdvice;
    /**
     * 申请单位和部门审核人id
     */
    private String deptOfficerId;
    /**
     * 申请单位和部门审核人名称
     */
    private String deptOfficerName;
    /**
     * 实验室审核意见(0:通过，1:不通过)
     */
    private Integer labAdvice;
    /**
     * 实验室审核意见人id
     */
    private String labOfficerId;
    /**
     * 实验室审核意见人名称
     */
    private String labOfficerName;
    /**
     * 创新合作部审核意见(0:通过，1:不通过)
     */
    private Integer chAdvice;
    /**
     * 创新合作部审核意见人id
     */
    private String chOfficerId;
    /**
     * 创新合作部审核意见人名称
     */
    private String chOfficerName;
    /**
     * 状态（1：正常、0：无效）
     */
    private String status;

}