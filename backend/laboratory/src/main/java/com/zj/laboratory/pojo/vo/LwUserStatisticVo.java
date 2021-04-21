package com.zj.laboratory.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class LwUserStatisticVo implements Serializable {

    /**
     * 柱名
     */
    private String name;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 数量
     */
    private BigDecimal count;

}
