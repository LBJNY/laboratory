package com.zj.laboratory.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@org.springframework.boot.context.properties.ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {

    private String appId;
    private String appSecret;
    private String grantType;
    private String loginUrl;

    public String getAuthUrl(String code) {
        // https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        return loginUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=" + grantType;
    }

}

