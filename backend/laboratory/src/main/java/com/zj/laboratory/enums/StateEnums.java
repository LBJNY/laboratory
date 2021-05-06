package com.zj.laboratory.enums;

import lombok.Getter;

/**
 * 状态码枚举。所有的状态码都在这里编写
 */
@Getter
public enum StateEnums {
    /**
     * 逻辑删除状态
     */
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),

    /**
     * 启用状态
     */
    ENABLED(1, "启用"),
    NOT_ENABLE(0, "未启用"),

    /**
     * 性别状态
     */
    SEX_MAN(1, "男"),
    SEX_WOMAN(2, "女"),

    /**
     * 请求访问状态枚举
     */
    REQUEST_SUCCESS(1, "请求正常"),
    REQUEST_ERROR(0, "请求异常"),

    /**
     * 菜单状态枚举
     */
    FOLDER(1, "目录"),
    MENU(2, "菜单"),
    AUTH(3, "权限"),
    /**
     * 绑定账户状态
     */
    EXISTS_USER(1, "绑定已有账户"),
    NEW_USER(2, "绑定新账户"),

    /**
     * 订单通用状态
     */
    ORDER_CONFIRM(0,"已提交"),
    ORDER_IN_APPROVAL(1,"审核中"),
    ORDER_AUDIT_SUCCESS(2,"成功"),
    ORDER_AUDIT_FAILED(3,"失败"),
    ORDER_AUDIT_OVER(4,"结束"),

    /**
     * 进场单订单审核状态
     */
    ENTRY_ORDER_PENDING(0,"待审核"),
    ENTRY_ORDER_PASS(1,"审核成功"),
    ENTRY_ORDER_FAIL(2,"审核失败"),
    /**
     * 进场单订单审核状态
     */
    SERVICE_ORDER_PENDING(0,"待审核"),
    SERVICE_ORDER_DEPT(1,"部门审核通过"),
    SERVICE_ORDER_LAB(2,"实验室审核通过"),
    SERVICE_ORDER_CHO(3,"创新合作部通过"),
    SERVICE_ORDER_FAIL(4,"审核失败"),

    /**
     * 审核反馈
     */
    /**
     * 进度
     */
    TEST_ADVANCE_ALL(0,"全部完成"),
    TEST_ADVANCE_PART(1,"部分完成"),
    TEST_ADVANCE_NONE(2,"没有完成"),
    /**
     *服务质量
     */
    TEST_QUALITY_GOOD(0,"很好"),
    TEST_QUALITY_ORDINARY(1,"一般"),
    TEST_QUALITY_POOR(2,"较差"),

    /**
     * 服务收费
     */
    TEST_CHARGE_REASONABLE(0,"非常合理"),
    TEST_CHARGE_ORDINARY(1,"基本合理"),
    TEST_CHARGE_NO(2,"不太合理"),

    /**
     * 满意度
     */
    TEST_SATISFACTION_SATISFIED(0,"非常满意"),
    TEST_SATISFACTION_ORDINARY(1,"基本满意"),
    TEST_SATISFACTION_NO(2,"不太满意"),
    ;

    private Integer code;
    private String msg;

    StateEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
