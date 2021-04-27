package com.zj.laboratory.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 进场单表(lw_entry)实体类
 *
 * @author lbj
 * @since 2021-04-21 09:59:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LwEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id号
     */
    private Long id;
    /**
     * 进场单编号
     */
    private String entryNo;
    /**
     * 智家接口人
     */
    private String entryManager;
    /**
     * 提交申请日期
     */
    private Date currentDate;
    /**
     * 单位和部门名称
     */
    private String deptName;
    /**
     * 服务项目名称
     */
    private String projName;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系人电话
     */
    private String tel;
    /**
     * 联系人邮箱
     */
    private String email;
    /**
     * 进场人员
     */
    private String staff;
    /**
     * 工作说明
     */
    private String description;
    /**
     * 资源需求
     */
    private String requirement;
    /**
     * 审核状态（0：待审核，1：通过，2：未通过）
     */
    private Integer verifyStatus;
    /**
     * 表锁（0：没锁，1：锁了）
     */
    private Integer lock;
    /**
     * 状态（1：正常，0：无效）
     */
    private Integer status;
    /**
     * 审核人id
     */
    private Long officerId;
    /**
     * 审核人名称
     */
    private String officerName;
    /**
     * 申请人id
     */
    private Long applicantId;
    /**
     * 申请人名称或昵称
     */
    private String applicantName;
    /**
     * 审核人状态（1：正常，0：无效）
     */
    private Integer officerStatus;
    /**
     * 申请人状态（1：正常，0：无效）
     */
    private Integer applicantStatus;
    /**
     * sDate
     */
    //修改传到前端的时间格式（不加就是时间戳）
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    //修改提交的时候前端传到后端的时间，即前端提交指定格式的字符串
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sDate;
    /**
     * eDate
     */
    //修改传到前端的时间格式（不加就是时间戳）
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    //修改提交的时候前端传到后端的时间，即前端提交指定格式的字符串
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date eDate;
    /**
     * 用户是否查看过：0没查看 1查看
     */
    private Integer isLook;
    /**
     * 是否删除，1是0否
     */
    private Integer deleted;

    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
}