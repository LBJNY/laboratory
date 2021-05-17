package com.zj.laboratory.controller;

import com.alibaba.fastjson.JSON;
import com.zj.laboratory.config.WeChatConfig;
import com.zj.laboratory.enums.ResultEnum;
import com.zj.laboratory.exception.LaboratoryException;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.pojo.TokenVo;
import com.zj.laboratory.pojo.WeChatResult;
import com.zj.laboratory.pojo.dto.EncryptDto;
import com.zj.laboratory.pojo.dto.WeChatRegisterDto;
import com.zj.laboratory.service.LwUserService;
import com.zj.laboratory.shiro.LwUserRealm;
import com.zj.laboratory.shiro.UserRealm;
import com.zj.laboratory.shiro.UserToken;
import com.zj.laboratory.utils.HttpUtils;
import com.zj.laboratory.utils.Result;
import com.zj.laboratory.utils.ShiroUtils;
import com.zj.laboratory.utils.WXCore;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信
 */
@RestController
@RequestMapping("/wx")
public class WeChatController {

    @Autowired
    private WeChatConfig weChatConfig;
    @Autowired
    private LwUserService lwUserService;

    @RequestMapping(value = "/wxLogin/{code}", method = RequestMethod.GET)
    public Result<Object> weLogin(@PathVariable String code) throws IOException {
        String body = HttpUtils.get(weChatConfig.getAuthUrl(code)).body();
        WeChatResult weChatResult = JSON.parseObject(body, WeChatResult.class);
        System.out.println(weChatResult.getSessionKey());
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UserToken(weChatResult.getOpenId(), weChatResult.getOpenId(), LwUserRealm.class);
        try {
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            // 说明openid对应用户不存在
            return new Result<>(weChatResult);
        }
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }

    /**
     * 注册登录
     *
     * @param weChatRegisterDto
     * @return
     */
    @RequestMapping(value = "/registerLogin", method = RequestMethod.POST)
    public Result<?> registerLogin(@RequestBody WeChatRegisterDto weChatRegisterDto) {
        LwUser lwUser = lwUserService.getByOpenId(weChatRegisterDto.getOpenId());
        if (lwUser == null) {
            // 注册
            lwUserService.register(weChatRegisterDto.toLwUser());
        }
        //  剩下的逻辑和登录一模一样
        // shiro登录
        Subject subject = SecurityUtils.getSubject();
        // 我们约定，openid为username，unionid为password
        AuthenticationToken authenticationToken = new UserToken(weChatRegisterDto.getOpenId(), weChatRegisterDto.getOpenId(), LwUserRealm.class);
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.LOGIN_ERROR);
        }
        // 获取sessionId
        Serializable token = subject.getSession().getId();
        return new Result<>(new TokenVo(null));
    }


    /**
     * 获取登录用户
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<LoginUser> info() {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        return new Result<>(loginUser);
    }

    /**
     * 获取登录用户手机号
     *
     * @return
     */
    @RequestMapping(value = "/bindPhoneNumber", method = RequestMethod.PUT)
    public Result<?> bindPhoneNumber(@RequestBody EncryptDto encryptDto) {
        //LoginUser loginUser = ShiroUtils.getLoginUser();
        try {
            String str = WXCore.decrypt(weChatConfig.getAppId(), encryptDto.getEncryptedData(), encryptDto.getSessionKey(), encryptDto.getIv());
            Map<String, String> map = (Map<String, String>) JSON.parse(str);
            System.out.println(map.toString());
            lwUserService.bindPhoneNumber(map.get("phoneNumber"));
        } catch (Exception e) {
            lwUserService.delUserById();
            throw new LaboratoryException("操作失败");
        }
        return new Result<>("绑定成功");
    }
}
