package com.zj.laboratory.enums;

import lombok.Getter;

@Getter
public enum  RoleEnum {
    /**
     * 逻辑删除状态
     */
    ADMIN(1, "审核员"),
    USER(0, "用户");

    private Integer type;
    private String des;
    RoleEnum(Integer type,String des) {
        this.type = type;
        this.des = des;
    }
}
