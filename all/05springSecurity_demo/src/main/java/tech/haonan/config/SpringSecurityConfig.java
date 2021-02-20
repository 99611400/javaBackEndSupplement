package tech.haonan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    // 定义用户信息服务(查询用户信息)
    @Bean
    public UserDetailsService userDetailsService(){
        // 先造两个假数据
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("123").authorities("a1","a2").build());
        manager.createUser(User.withUsername("wangwu").password("1456").authorities("a1").build());
        return manager;
    }


//    // 密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new NoOpPasswordEncoder;
//    }


    // 安全拦截机制
}
