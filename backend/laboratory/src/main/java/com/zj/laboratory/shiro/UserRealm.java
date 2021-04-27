package com.zj.laboratory.shiro;

import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.service.LwUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LwUserService lwUserService;

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserToken userToken = (UserToken) token;
        String username = userToken.getUsername();
        LwUser user = lwUserService.getByOpenId(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在！");
        }
        LoginUser loginUser = user.toLoginUser();
        return new SimpleAuthenticationInfo(loginUser, user.getOpenId(), getName());
    }
}