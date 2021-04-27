package com.zj.laboratory.pojo.vo;

import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.pojo.LwOrderAudit;
import com.zj.laboratory.pojo.LwUser;
import lombok.Data;

import java.util.Date;

@Data
public class LwServiceOrderVo {
    /**
     * id
     */
    private Long id;
    /**
     * 服务委托单编号
     */
    private String serviceNo;
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
     * 电话
     */
    private String tel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 服务内容描述
     */
    private String description;
    /**
     * 智家公司接口人
     */
    private String serviceManager;
    /**
     * 申请日期
     */
    private Date currentDate;
    /**
     * 申请人id
     */
    private Long applicationId;
    /**
     * 申请人名称
     */
    private String applicantName;
    /**
     * 时间和周期要求
     */
    private String timeReq;
    /**
     * 场地和网络要求
     */
    private String netReq;
    /**
     * 平台和软件要求
     */
    private String softReq;
    /**
     * 终端和仪表要求
     */
    private String deviceReq;
    /**
     * 服务人员要求（0-0-0-0-0）
     */
    private String personReq;
    /**
     * 其他资源要求
     */
    private String otherReq;
    /**
     * 服务类型
     */
    private String serviceType;
    /**
     * 资金支持和费用承担
     */
    private String fundSupport;
    /**
     * 审核状态（0：待审核，1：部门审核通过，2：实验室审核通过;3：创合部通过；4：未通过）
     */
    private Integer verifyStatus;

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
        if (StateEnums.SERVICE_ORDER_DEPT.getCode() == verifyStatus) {
            this.verifyStatusName=StateEnums.SERVICE_ORDER_DEPT.getMsg();
        } else if (StateEnums.SERVICE_ORDER_LAB.getCode() == verifyStatus) {
            this.verifyStatusName=StateEnums.SERVICE_ORDER_LAB.getMsg();
        } else if (StateEnums.SERVICE_ORDER_CHO.getCode() == verifyStatus) {
            this.verifyStatusName=StateEnums.SERVICE_ORDER_CHO.getMsg();
        }  else {
            this.verifyStatusName=StateEnums.SERVICE_ORDER_FAIL.getMsg();
        }
    }

    /**
     * 状态名称
     */
    private String verifyStatusName;

    /**
     * 状态（1：正常、0：无效）
     */
    private Integer status;
    /**
     * 是否反馈过（0：反馈过  1：没有反馈过）
     */
    private Integer isFeedBack;
    /**
     * 当前审核人id
     */
    private Long currentReviewerId;
    /**
     * 当前审核人名称
     */
    private String currentReviewerName;
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

    /**
     * 审核信息
     */
    private LwOrderAudit lwOrderAudit;
    /**
     * 反馈信息
     */
    private LwOrderFeedbackVo lwOrderFeedbackVo;
}
