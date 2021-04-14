package com.zj.laboratory.enums;

import lombok.Getter;

@Getter
public enum SortTypeEnum {
    /**
     * 正序
     */
    ASC(1),
    /**
     * 倒序
     */
    DESC(2);

    private Integer num;

    SortTypeEnum(Integer num) {
        this.num = num;
    }
}