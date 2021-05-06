package com.zj.laboratory.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.pojo.LwUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
public class LwEntryOrderVo {
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

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
        if (StateEnums.ENTRY_ORDER_PASS.getCode() == verifyStatus) {
            this.verifyStatusName=StateEnums.ENTRY_ORDER_PASS.getMsg();
        } else if (StateEnums.ENTRY_ORDER_FAIL.getCode() == verifyStatus) {
            this.verifyStatusName=StateEnums.ENTRY_ORDER_FAIL.getMsg();
        } else {
            this.verifyStatusName=StateEnums.ENTRY_ORDER_PENDING.getMsg();
        }
    }

    /**
     * 状态名称
     */
    private String verifyStatusName;

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
     * 申请人名称
     */
    private String applicantName;
    /**
     * sDate
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    //修改提交的时候前端传到后端的时间，即前端提交指定格式的字符串
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sDate;
    /**
     * eDate
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    //修改提交的时候前端传到后端的时间，即前端提交指定格式的字符串
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date eDate;

    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 小程序端进度条
     */
    private List<LwOrderProgressVo> progress;
}
