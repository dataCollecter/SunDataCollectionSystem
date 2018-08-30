package cn.edu.scau.DataCollectionSystem.config;

import cn.edu.scau.DataCollectionSystem.dao.UserDao;
import cn.edu.scau.DataCollectionSystem.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*
            顺序拦截
         */
        filterChainDefinitionMap.put("/DataCollectionSystem/index", "anon");
        filterChainDefinitionMap.put("/DataCollectionSystem/error", "anon");
        filterChainDefinitionMap.put("/DataCollectionSystem/login", "anon");

        filterChainDefinitionMap.put("/DataCollectionSystem/css/**", "anon");
        filterChainDefinitionMap.put("/DataCollectionSystem/fonts/**", "anon");
        filterChainDefinitionMap.put("/DataCollectionSystem/img/**", "anon");
        filterChainDefinitionMap.put("/DataCollectionSystem/js/**", "anon");

        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setLoginUrl("/DataCollectionSystem/index");
        shiroFilterFactoryBean.setSuccessUrl("/DataCollectionSystem/admin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/DataCollectionSystem/index");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager(SunRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public SunRealm myShiroRealm() {
        return new SunRealm();
    }
}

class SunRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    public String getName() {
        return "SunRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User u = userDao.findAll(User.class).get(0);
        return new SimpleAuthenticationInfo(
                "", //个人系统验证不需要用户名
                u.getPassword(),
                getName());
    }
}
