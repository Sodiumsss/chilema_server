package com.yoyo.chilema_server.controller;

import com.alibaba.fastjson2.JSONObject;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@RestController
public class UserController {

    @Autowired
    private UserAccountService userAccountService;


    @CrossOrigin
    @PostMapping("/create")
    public R create(String info , HttpServletRequest httpServletRequest) {
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


    @CrossOrigin
    @PostMapping("/login")
    public R login(String info,HttpServletRequest httpServletRequest)
    {
        if (!httpServletRequest.getHeader("Created").equals("yoyo!")){return R.error("404 NOTFOUND:(");}
        if (info==null){return R.error("404 NOTFOUND:(");}
        byte[] base64decodedBytes = Base64.getDecoder().decode(info);
        JSONObject jsonObj = JSONObject.parseObject(new String(base64decodedBytes));
        System.out.println(jsonObj);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(jsonObj.getString("username"));
        userAccount.setPassword(jsonObj.getString("password"));
        return userAccountService.login(userAccount);
    }



}
