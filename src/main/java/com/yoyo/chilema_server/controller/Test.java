package com.yoyo.chilema_server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Test {


//    @Resource
//    private RedisTemplate<String,Object> redisTemplate;
//    @RequestMapping("/test")
//    public String test(String key, HttpServletRequest httpServletRequest)
//    {
//        Object v =redisTemplate.opsForValue().get(key);
//        return "Key:"+key+" Get:"+v+"\n"+ httpServletRequest.getHeader("User-Agent");
//    }


    @PostMapping("/add")
    @CrossOrigin
    public String add (String info ,HttpServletRequest httpServletRequest)
    {
        if (!httpServletRequest.getHeader("Created").equals("yoyo!")){return "404 NOTFOUND:(";}
        if (info==null){return "404 NOTFOUND:(";}
        return info;
    }



}
