package com.zj.laboratory.enums;

import lombok.Getter;

@Getter
public enum OrderSortEnum {

    /**
     * 进度排序 默认排序
     */
    PROGRESS(1, "progress"),
    /**
     * 时间排序
     */
    TIME(2, "time");


    private Integer type;
    private String column;

    OrderSortEnum(Integer type, String column) {
        this.type = type;
        this.column = column;
    }

    /**
     * 根据type获取列名
     *
     * @param type
     * @return
     */
    public static String getSort(Integer type) {
        switch (type) {
            case 1:
                return "progress";
            case 2:
                return "time";
            default:
                return "";
        }
    }
}
