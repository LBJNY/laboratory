package com.zj.laboratory.pojo.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderMonthVo implements Serializable {

    private String day;

    private Integer count;

}
