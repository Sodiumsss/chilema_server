package com.yoyo.chilema_server.controller;

import com.alibaba.fastjson2.JSONObject;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.Favor;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.FavorService;
import com.yoyo.chilema_server.service.UserAccountService;
import com.yoyo.chilema_server.utils.JsonUtils;
import com.yoyo.chilema_server.utils.RequestDataUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private FavorService favorService;

    @PostMapping("/api/user/create")
    @CrossOrigin
    public R createUser(String info) {
        String requestInfo = RequestDataUtils.decodeBase64Info(info);

        JSONObject obj = (JSONObject) JsonUtils.getJsonObj(requestInfo, "UserAccount");
        UserAccount userAccount = JSONObject.parseObject(String.valueOf(obj), UserAccount.class);
        JSONObject obj2 = (JSONObject) JsonUtils.getJsonObj(requestInfo, "Favor");
        System.out.println(obj2);
        Favor favor = new Favor(
                0,
                userAccount.getUsername(),
                obj2.get("step1").toString(),
                obj2.get("step2").toString(),
                obj2.get("step3").toString(),
                obj2.get("step4").toString());
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
    public R updateFavor(String info) {
        Favor favor = RequestDataUtils.decodeInfo(info, Favor.class);
        return favorService.updateFavor(favor);
    }

    @PostMapping("/api/user/update")
    @CrossOrigin
    public R updateUser(String info) {
        UserAccount userAccount = RequestDataUtils.decodeInfo(info, UserAccount.class);
        return userAccountService.updateUserAccount(userAccount);
    }

    @CrossOrigin
    @PostMapping("/api/user/login")
    public R login(String info)
    {
        UserAccount userAccount = RequestDataUtils.decodeInfo(info, UserAccount.class);
        return userAccountService.login(userAccount);
    }


    @CrossOrigin
    @PostMapping("/api/user/forgetPW")
    public R forgetPW(String info)
    {
        UserAccount RAccount = RequestDataUtils.decodeInfo(info,UserAccount.class);

        UserAccount SAccount =userAccountService.selectUserAccountByUsername(RAccount.getUsername());

        if (SAccount==null)
        {
            return R.error("填写错误！");
        }

        if (RAccount.getSchoolId().equals(SAccount.getSchoolId()))
        {
            if(RAccount.getBirthYear().equals(SAccount.getBirthYear()))
            {
                SAccount.setPassword(RAccount.getPassword());
                return userAccountService.updateUserAccount(SAccount);
            }
        }

        return R.error("填写错误！");
    }

    @CrossOrigin
    @PostMapping("/api/user/getNickname")
    public R getNickname(String info)
    {
        UserAccount userAccount = RequestDataUtils.decodeInfo(info, UserAccount.class);
        return userAccountService.getUserNickname(userAccount);
    }


    @CrossOrigin
    @PostMapping("/api/user/verifyUsername")
    public R verifyUsername(String info)
    {
        UserAccount userAccount = RequestDataUtils.decodeInfo(info, UserAccount.class);
        UserAccount saved=userAccountService.selectUserAccountByUsername(userAccount.getUsername());
        if (saved==null)
        {
            return R.success();
        }
        else
        {
            return R.error();
        }
    }

}
