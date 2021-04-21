package com.zj.laboratory.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户登录返回信息表
 */
@Data
public class LoginUser implements Serializable {


    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 微信小程序openid
     */
    private String openId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 名称
     */
    private String name;
    /**
     * 头像
     */
    private String header;

    /**
     * 最后登录时间
     */
    private String loginTime;
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
     * 权限列表
     */
    private List<String> auths;

}