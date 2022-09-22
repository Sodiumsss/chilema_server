package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@RestController
public class Test {

    @Autowired
    private UserAccountService userAccountService;



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
    public String add (String info ,HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        if (!httpServletRequest.getHeader("Created").equals("yoyo!")){return "404 NOTFOUND:(";}
        if (info==null){return "404 NOTFOUND:(";}
        byte[] base64decodedBytes = Base64.getDecoder().decode(info);
        return info;
    }



}
