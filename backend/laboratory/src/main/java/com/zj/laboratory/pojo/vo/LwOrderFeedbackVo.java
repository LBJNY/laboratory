package com.zj.laboratory.pojo.vo;

import com.zj.laboratory.enums.StateEnums;

import java.util.Date;

public class LwOrderFeedbackVo {
    /**
     * id
     */
    private Long id;
    /**
     * 服务委托单编号
     */
    private String serviceNo;
    /**
     * 测试服务进度（0：全部完成、1：部分完成、2：没有完成）
     */
    private Integer serviceAdvance;

    public void setServiceAdvance(Integer serviceAdvance) {
        this.serviceAdvance = serviceAdvance;
        if (StateEnums.TEST_ADVANCE_ALL.getCode()==serviceAdvance){
            this.serviceAdvanceName=StateEnums.TEST_ADVANCE_ALL.getMsg();
        }else if (StateEnums.TEST_ADVANCE_PART.getCode()==serviceAdvance){
            this.serviceAdvanceName=StateEnums.TEST_ADVANCE_PART.getMsg();
        }else if (StateEnums.TEST_ADVANCE_NONE.getCode()==serviceAdvance){
            this.serviceAdvanceName=StateEnums.TEST_ADVANCE_NONE.getMsg();
        }
    }

    private String serviceAdvanceName;
    /**
     * 测试服务质量（0：很好、1：一般、2：较差）
     */
    private Integer serviceQuality;
    public void setServiceQualityName(Integer serviceQuality) {
        this.serviceQuality = serviceQuality;
        if (StateEnums.TEST_QUALITY_GOOD.getCode()==serviceQuality){
            this.serviceAdvanceName=StateEnums.TEST_QUALITY_GOOD.getMsg();
        }else if (StateEnums.TEST_QUALITY_ORDINARY.getCode()==serviceQuality){
            this.serviceAdvanceName=StateEnums.TEST_QUALITY_ORDINARY.getMsg();
        }else if (StateEnums.TEST_QUALITY_POOR.getCode()==serviceQuality){
            this.serviceAdvanceName=StateEnums.TEST_QUALITY_POOR.getMsg();
        }
    }

    private String serviceQualityName;
    /**
     * 测试服务收费（0：非常合理、1：基本合理、2：不太合理）
     */
    private Integer serviceCharge;

    public void setServiceCharge(Integer serviceCharge) {
        this.serviceCharge = serviceCharge;
        if (StateEnums.TEST_CHARGE_REASONABLE.getCode()==serviceCharge){
            this.serviceAdvanceName=StateEnums.TEST_CHARGE_REASONABLE.getMsg();
        }else if (StateEnums.TEST_CHARGE_ORDINARY.getCode()==serviceCharge){
            this.serviceAdvanceName=StateEnums.TEST_CHARGE_ORDINARY.getMsg();
        }else if (StateEnums.TEST_CHARGE_NO.getCode()==serviceCharge){
            this.serviceAdvanceName=StateEnums.TEST_CHARGE_NO.getMsg();
        }
    }

    private String serviceChargeName;
    /**
     * 满意度（0：非常满意、1：基本满意、3：不太满意）
     */
    private Integer satisfaction;

    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
        if (StateEnums.TEST_SATISFACTION_SATISFIED.getCode()==satisfaction){
            this.serviceAdvanceName=StateEnums.TEST_SATISFACTION_SATISFIED.getMsg();
        }else if (StateEnums.TEST_SATISFACTION_ORDINARY.getCode()==satisfaction){
            this.serviceAdvanceName=StateEnums.TEST_SATISFACTION_ORDINARY.getMsg();
        }else if (StateEnums.TEST_SATISFACTION_NO.getCode()==satisfaction){
            this.serviceAdvanceName=StateEnums.TEST_SATISFACTION_NO.getMsg();
        }
    }

    private String satisfactionName;
    /**
     * 其他建议
     */
    private String suggestions;
    /**
     * 申请单位和部门完工确认签字
     */
    private String signature;
    /**
     * 完工日期
     */
    private Date completion;
    /**
     * 状态（1：正常、0：无效）
     */
    private Integer status;
}
