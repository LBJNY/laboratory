package com.zj.laboratory.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthorizingRealm;

public class UserToken extends UsernamePasswordToken {

    private Class<? extends AuthorizingRealm> userType;

    public UserToken(final String username, final String password, Class<? extends AuthorizingRealm> userType) {
        super(username, password);
        this.userType = userType;
    }

    public Class<?> getUserType() {
        return userType;
    }

    public void setUserType(Class<? extends AuthorizingRealm> userType) {
        this.userType = userType;
    }

}