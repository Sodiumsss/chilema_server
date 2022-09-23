package com.yoyo.chilema_server.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.Favor;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.FavorService;
import com.yoyo.chilema_server.service.UserAccountService;
import com.yoyo.chilema_server.utils.JsonUtils;
import com.yoyo.chilema_server.utils.RequestDataUtils;
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

    @Autowired
    private FavorService favorService;

    @PostMapping("/api/user/create")
    @CrossOrigin
    public R addUser(String info, HttpServletRequest request) {
        String requestInfo = RequestDataUtils.decodeBase64Info(info);
        JSONObject obj = (JSONObject) JsonUtils.getJsonObj(requestInfo, "UserAccount");
        UserAccount userAccount = JSONObject.parseObject(String.valueOf(obj), UserAccount.class);
        JSONObject obj2 = (JSONObject) JsonUtils.getJsonObj(requestInfo, "Favor");
        System.out.println(obj2);
        Object username = obj.get("username");
        Object step1 = obj2.get("step1");
        Object step2 = obj2.get("step2");
        Object step3 = obj2.get("step3");
        Object step4 = obj2.get("step4");
        Favor favor = new Favor(0 , username.toString(), step1.toString(), step2.toString(), step3.toString(), step4.toString());
        if(userAccountService.addUserAccount(userAccount).getCode()+favorService.addFavor(favor).getCode() == 2) {
            return R.success("注册成功");
        } else {
            return R.error("注册失败");
        }
    }

    @PostMapping("/api/user/delete")
    @CrossOrigin
    public R deleteUser(String username) {
        if(userAccountService.deleteUserAccountByUN(username).getCode()+favorService.deleteFavorByUN(username).getCode() == 2) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @PostMapping("/api/favor/update")
    @CrossOrigin
    public R updateFavor(String info,HttpServletRequest request) {
        Favor favor = RequestDataUtils.decodeInfo(info, Favor.class);
        return favorService.updateFavor(favor);
    }

    @PostMapping("/api/user/update")
    @CrossOrigin
    public R updateUser(String info,HttpServletRequest request) {
        UserAccount userAccount = RequestDataUtils.decodeInfo(info, UserAccount.class);
        return userAccountService.updateUserAccount(userAccount);
    }

    @CrossOrigin
    @PostMapping("/api/user/login")
    public R login(String info,HttpServletRequest httpServletRequest)
    {
        UserAccount userAccount = RequestDataUtils.decodeInfo(info, UserAccount.class);
        return userAccountService.login(userAccount);
    }

    @CrossOrigin
    @PostMapping("/api/user/forgetPW")
    public R forgetPW(String info,HttpServletRequest httpServletRequest)
    {
        String decodeBase64Info = RequestDataUtils.decodeBase64Info(info);
        JSONObject jsonObj = JSONObject.parseObject(decodeBase64Info);
        String username=jsonObj.getString("username");
        UserAccount account = userAccountService.selectUserAccountByUsername(username);
        if (account==null)
        {
            return R.error("填写错误！");
        }

        Integer schoolId=jsonObj.getInteger("schoolId");
        Integer birthYear=jsonObj.getInteger("birthYear");
        String password=jsonObj.getString("password");

        if (schoolId.equals(account.getSchoolId()) && birthYear.equals(account.getBirthYear()))
        {
            account.setPassword(password);
            return userAccountService.updateUserAccount(account);
        }

        return R.error("填写错误！");
    }




}
