package com.yoyo.chilema_server.controller;

import com.alibaba.fastjson2.JSONObject;
import com.yoyo.chilema_server.common.R;
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



    @PostMapping("/add")
    @CrossOrigin
    public R add (String info , HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        if (!httpServletRequest.getHeader("Created").equals("yoyo!")){return R.error("404 NOTFOUND:(");}
        if (info==null){return R.error("404 NOTFOUND:(");}
        byte[] base64decodedBytes = Base64.getDecoder().decode(info);
        JSONObject jsonObj = JSONObject.parseObject(new String(base64decodedBytes));
        System.out.println(jsonObj);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(jsonObj.getString("username"));
        userAccount.setPassword(jsonObj.getString("password"));
        userAccount.setSchoolId(jsonObj.getInteger("schoolId"));
        userAccount.setBirthYear(jsonObj.getInteger("birthYear"));
        System.out.println(userAccount);
        return userAccountService.addUserAccount(userAccount);
    }





}
