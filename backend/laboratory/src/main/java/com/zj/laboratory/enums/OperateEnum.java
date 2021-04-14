package com.zj.laboratory.enums;

import lombok.Getter;


@Getter
public enum  OperateEnum {
    /**
     * 操作用户
     */
    USER("用户"),
    SYSTEM("系统"),
    ADMIN("管理员")
    ;

    private String type;

    OperateEnum(String type) {
        this.type = type;
    }
}
