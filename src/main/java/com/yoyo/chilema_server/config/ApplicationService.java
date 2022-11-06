package com.yoyo.chilema_server.config;

import com.yoyo.chilema_server.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationService implements ApplicationRunner {

    @Autowired
    RedisUtils redisUtils;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisUtils.init();
        System.out.println("已初始化");
    }
}
