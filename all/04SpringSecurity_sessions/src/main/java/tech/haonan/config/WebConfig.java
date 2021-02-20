package tech.haonan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.haonan.interceptor.MyAuthenticationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    MyAuthenticationInterceptor myAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器生效  而且不是每一个接口都拦截 登录接口是不拦截的
        registry.addInterceptor(myAuthenticationInterceptor)
        .excludePathPatterns("/test/sessionLogin");
    }
}
