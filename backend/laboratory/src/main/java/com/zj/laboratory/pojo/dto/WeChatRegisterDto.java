package com.zj.laboratory.pojo.dto;

import com.zj.laboratory.pojo.LwUser;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信注册
 */
@Data
public class WeChatRegisterDto implements Serializable {

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * openId
     */
    private String openId;

    /**
     * 转换成会员对象
     *
     * @return
     */
    public LwUser toLwUser() {
        LwUser lwUser = new LwUser();
        lwUser.setHeader(avatarUrl);
        lwUser.setGender(gender);
        lwUser.setNickname(nickName);
        lwUser.setOpenId(openId);
        return lwUser;
    }

}