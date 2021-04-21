package com.zj.laboratory.pojo.vo;

import com.zj.laboratory.pojo.LwUserStatistic;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class LwUserVo implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 名称
     */
    private String name;

    /**
     * 1正常0封禁
     */
    private Integer status;

    /**
     * 头像
     */
    private String header;

    /**
     * 性别，1男2女
     */
    private Integer gender;

    /**
     * 个性签名
     */
    private String note;

    /**
     * 审核员类型
     */
    private Integer reviewerType;
    /**
     * 等级:
     * 0:用户
     * 1:服务委托单
     * 2:进场单
     * 3:管理员都可
     */
    private Integer level;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 服务委托单数量  默认0
     */
    private Integer serviceNumber;
    /**
     * 进场单数量   默认0
     */
    private Integer entryNumber;

    private LwUserStatistic lwUserStatistic;
}
