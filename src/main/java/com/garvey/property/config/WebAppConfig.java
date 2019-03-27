package com.garvey.property.config;

import com.garvey.property.interceptor.AuthorityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/");
    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
