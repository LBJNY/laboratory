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


@Component("lwUserRealm")
public class LwUserRealm extends AuthorizingRealm {

    @Autowired
    private LwUserService lwUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken userToken = (UserToken) authenticationToken;
        String username = userToken.getUsername();
        LwUser user = lwUserService.getByOpenId(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在！");
        }
        LoginUser loginUser = user.toLoginUser();
        return new SimpleAuthenticationInfo(loginUser, user.getOpenId(), getName());
    }
}
