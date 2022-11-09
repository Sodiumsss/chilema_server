package com.yoyo.chilema_server.config;

import com.yoyo.chilema_server.Interceptor.UserTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    UserTokenInterceptor userTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenInterceptor)
                .addPathPatterns("/api/user/login")
                .addPathPatterns("/api/hollow/**")
                .addPathPatterns("/api/user/verifyUsername")
                .addPathPatterns("/api/user/verifyUsername")
                .addPathPatterns("/api/user/changeNickname")
                .addPathPatterns("/api/user/forgetPW")
                .addPathPatterns("/api/user/getByToken")
                .addPathPatterns("/api/user/joinHollow")
                .addPathPatterns("/api/food/getSingleFood")
                .addPathPatterns("/api/user/create");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/foodPic/**")
                .addResourceLocations("file:" +  System.getProperty("user.dir") + "/img/foodPic/");
    }
}
