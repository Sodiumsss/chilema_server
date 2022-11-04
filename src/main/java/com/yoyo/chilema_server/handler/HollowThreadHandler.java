package com.yoyo.chilema_server.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class HollowThreadHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(),metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
