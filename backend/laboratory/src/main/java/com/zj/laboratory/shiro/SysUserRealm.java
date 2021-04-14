package com.zj.laboratory.shiro;


import com.zj.laboratory.enums.ResultEnum;
import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.exception.LaboratoryException;
import com.zj.laboratory.mapper.SysMenuMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.SysUser;
import com.zj.laboratory.pojo.vo.SysUserVo;
import com.zj.laboratory.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * 系统用户登录realm
 */
@Component("sysUserRealm")
public class SysUserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuMapper sysMenuMapper;
    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection principalCollection) {
        // 获取登录用户
        SysUserVo sysUserVo = (SysUserVo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(new HashSet<>(sysUserVo.getAuths()));
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
        //处理登录逻辑
        UserToken userToken= (UserToken) token;
        String username=userToken.getUsername();
        SysUser sysUser = sysUserService.getByUserName(username);
        if (sysUser==null){
            throw new LaboratoryException(ResultEnum.LOGIN_PARAM_ERROR);
        }
        if (StateEnums.NOT_ENABLE.getCode().equals(sysUser.getStatus())){
            //未启用用户
            throw new LaboratoryException(ResultEnum.LOGIN_PARAM_ERROR);
        }
        if (StateEnums.DELETED.getCode().equals(sysUser.getDeleted())){
            //已删除用户
            throw new LaboratoryException(ResultEnum.LOGIN_PARAM_ERROR);
        }
        // 创建SYsUserVo拷贝属性
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        // 在这里查询权限
        java.util.List<String> auths = sysMenuMapper.getMenuCodeByUserId(sysUser.getId());
        if (CollectionUtils.isEmpty(auths)) {
            throw new LaboratoryException("当前用户不具备任何权限，禁止登录");
        }
        loginUser.setAuths(auths);
        return new SimpleAuthenticationInfo(loginUser,sysUser.getPassword(),this.getName());
    }

}