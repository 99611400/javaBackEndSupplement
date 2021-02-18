package tech.haonan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.haonan.interceptors.JWTInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                // 拦截一切其他请求
                .addPathPatterns("/**")
                // 只放行登录
                .excludePathPatterns("/user/loginTest");
    }
}
