package com.yoyo.chilema_server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    public Long alive(String key)//返回剩余时间，单位秒
    {
        return stringRedisTemplate.getExpire(key);
    }

    public Boolean expire(String key, long timeout)//设置过期时间，单位秒
    {
       return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
    public void init() //删除key
    {
        Set<String> keys=redisTemplate.keys("Acc::"+"*");
        redisTemplate.delete(keys);
    }

    public Boolean delete(String key) //删除key
    {
        return stringRedisTemplate.delete(key);
    }

    public void set(String key, String value)
    {
        stringRedisTemplate.opsForValue().set(key, value);
    }
    public void set(String key, String value, long timeout)
    {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }


    public String get(String key)
    {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void incr(String key)
    {
        stringRedisTemplate.opsForValue().increment(key);
    }

    public void decr(String key)
    {
        stringRedisTemplate.opsForValue().decrement(key);
    }

    public Long sAdd(String key, String... values)
    {
        return redisTemplate.opsForSet().add(key,values);
    }
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }


    public Set<String> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }
    public Boolean hasKey(String key)
    {
        return stringRedisTemplate.hasKey(key);
    }



}
