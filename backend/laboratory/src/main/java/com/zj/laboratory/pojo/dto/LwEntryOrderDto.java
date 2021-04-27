package com.zj.laboratory.pojo.dto;

import lombok.Data;

@Data
public class LwEntryOrderDto {

    /**
     * 审核状态（0：待审核，1：通过，2：未通过）
     */
    private Integer verifyStatus;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系人电话
     */
    private String tel;
    /**
     * 服务项目名称
     */
    private String projName;
}
