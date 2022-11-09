package com.yoyo.chilema_server.config;

import com.yoyo.chilema_server.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ApplicationService implements ApplicationRunner {

    @Autowired
    RedisUtils redisUtils;

    @Override
    public void run(ApplicationArguments args) {
        log.info("图片真实路径："+
                System.getProperty("user.dir") + System.getProperty("file.separator") + "img" +
                System.getProperty("file.separator") + "foodPic");
        redisUtils.init();
    }
}
