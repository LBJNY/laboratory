package com.zj.laboratory.enums;

import lombok.Getter;

@Getter
public enum  RoleEnum {
    /**
     * 页面类型
     */
    ADMIN(1, "审核员"),
    USER(0, "用户"),

    /**
     * 审查等级
     */
    LEVEL_USER(1,"普通用户"),
    LEVEL_SERVICE_ORDER(2,"服务委托单审核员"),
    LEVEL_ENTRY_ORDER(3,"进场单审核员"),
    LEVEL_ADMIN(0,"顶级审核员"),

    /**
     * 审查人类型
     */
    REVIEWER_TYPE_USER(1,"普通用户"),
    REVIEWER_TYPE_DEPT(2,"申请单位和部门审核审核员"),
    REVIEWER_TYPE_LAB(3,"实验室审核审核员"),
    REVIEWER_TYPE_CH(4,"创新合作部审核审核员"),
    REVIEWER_TYPE_ADMIN(0,"顶级审核员"),
    ;
    private Integer type;
    private String des;
    RoleEnum(Integer type,String des) {
        this.type = type;
        this.des = des;
    }
}
