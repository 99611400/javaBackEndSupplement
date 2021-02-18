package tech.haonan.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.haonan.shiro.realms.TestRealm;

import java.util.HashMap;
import java.util.Map;

/***
 * 整合shiro的配置
 */
@Configuration
public class ShiroConfig {
    // 1. 创建shiroFilter  负责拦截请求

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean  shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 配置系统的受限资源和公共资源
        Map<String ,String > map = new HashMap<>();
        // 除了 /user/login这个接口之外 其他一律进行验证后才能访问
        map.put("/user/login","anon");
        map.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        // 设置 登录接口是 /user/login 访问受限资源的时候会跳回到这里
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        return shiroFilterFactoryBean;
    }

    // 2. 创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    // 3. 创建自定义 realm
    @Bean
    public Realm getRealm(){
        TestRealm testRealm = new TestRealm();
        return testRealm;
    }
}
