package com.zj.laboratory.pojo.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderPointVo implements Serializable {

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 中文状态
     */
    private String statusMsg;

    /**
     * 订单数
     */
    private Integer count;

}
