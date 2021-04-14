package com.zj.laboratory.shiro;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;
import javax.servlet.Filter;

@Configuration
public class ShiroConfig {
    @Autowired
    private SysUserRealm sysUserRealm;

    @Autowired
    private ShopUserRealm shopUserRealm;

    @org.springframework.context.annotation.Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
        //设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //把自定义过滤器放到shiro中
        Map<String, Filter> shiroFilters = shiroFilterFactoryBean.getFilters();
        shiroFilters.put("authc", new LoginFilter());
        //配置需要认证或者放行的路径
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/sysUser/login", "anon");
        filterMap.put("/sysBanner/getBannerList", "anon");
        filterMap.put("/sysNotice/getNoticeList", "anon");
        filterMap.put("/wx/**", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public org.apache.shiro.mgt.SecurityManager securityManager(SessionDaoConfig sessionDaoConfig) {
        DefaultWebSecurityManager def = new DefaultWebSecurityManager();
        def.setAuthenticator(modularRealmAuthenticator());
        List<org.apache.shiro.realm.Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(sysUserRealm);
        realms.add(shopUserRealm);
        def.setRealms(realms);
        // 自定义session管理 使用redis
        TokenWebSessionManager sessionConfig = new TokenWebSessionManager();
        sessionConfig.setSessionDAO(sessionDaoConfig);
        def.setSessionManager(sessionConfig);
        return def;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        //自己重写的ModularRealmAuthenticator
        PochiAuthenticator modularRealmAuthenticator = new PochiAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    /**
     * 管理生命周期
     * 如果不注入可能会无法启动
     * 注意方法要是静态的
     *
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 使用redis存sessionId
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public org.springframework.data.redis.core.RedisTemplate<Object, Object> redisTemplate(org.springframework.data.redis.connection.RedisConnectionFactory connectionFactory) {
        org.springframework.data.redis.core.RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(org.springframework.data.redis.connection.RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

    /**
     * 加入注解的使用，不加入注解不生效
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
