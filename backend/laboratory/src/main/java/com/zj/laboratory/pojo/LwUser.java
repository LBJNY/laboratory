package com.zj.laboratory.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 小程序用户表(lw_user)实体类
 *
 * @author lbj
 * @since 2021-04-19 09:13:37
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LwUser implements Serializable {

    private static final long serialVersionUID = 981816007678159811L;

    /**
     * id号
     */
    private Long id;

    /**
     * 用户唯一openid
     */
    private String openId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 性别:1:男 2: 女
     */
    private Integer gender;
    /**
     * 头像url
     */
    private String header;
    /**
     * 姓名--后台给
     */
    private String name;
    /**
     * 有效（1：有效，0：无效）
     */
    private Integer status;
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
    private Date createTime;
    /**
     * 创建时间
     */
    private Date updateTime;
    /**
     * 是否删除，1是0否
     */
    private Integer deleted;
    /**
     * 服务委托单数量  默认0
     */
    private Integer serviceNumber;
    /**
     * 进场单数量   默认0
     */
    private Integer entryNumber;

    public LoginUser toLoginUser() {
        LoginUser user = new LoginUser();
        user.setId(id);
        user.setUsername(phone);
        user.setName(name);
        user.setNickName(nickname);
        user.setHeader(header);
        user.setOpenId(openId);
        user.setReviewerType(reviewerType);
        user.setLevel(level);
        return user;
    }

}
