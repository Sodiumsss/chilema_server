package com.yoyo.chilema_server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public Long alive(String key)//返回剩余时间，单位秒
    {
        return redisTemplate.getExpire(key);
    }

    public Boolean expire(String key, long timeout)//设置过期时间，单位秒
    {
       return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public Boolean delete(String key) //删除key
    {
        return redisTemplate.delete(key);
    }

    public void set(String key, String value)
    {
        redisTemplate.opsForValue().set(key, value);
    }
    public void set(String key, String value, long timeout)
    {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    public String get(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }



}
