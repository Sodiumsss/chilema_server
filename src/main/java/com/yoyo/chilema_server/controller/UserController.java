package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.UserWithFavor;
import com.yoyo.chilema_server.pojo.Favor;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.FavorService;
import com.yoyo.chilema_server.service.UserAccountService;
import com.yoyo.chilema_server.utils.RedisUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    RedisUtils redisUtils;
    @Resource
    private FavorService favorService;

    @PostMapping("/api/user/create")
    @CrossOrigin
    public R createUser(@RequestBody UserWithFavor userWithFavor) {

        UserAccount userAccount=userWithFavor.getUserAccount();
        Favor favor=userWithFavor.getFavor();
        try {
            if (userAccountService.addUserAccount(userAccount).getCode()==1)
            {
                favor.setId(userAccount.getId());
                if (favorService.addFavor(favor).getCode()==1)
                {
                    return R.success();
                }
                else
                {
                    return R.error();
                }
            }
            else
            {
                return R.error();
            }
        }
        catch (Exception e)
        {
            return R.error("注册失败");
        }

    }
    @PostMapping("/api/user/delete")
    @CrossOrigin
    public R deleteUser(@RequestBody Long id) {
        if(favorService.deleteFavorById(id).getCode() == 1)
        {
            return userAccountService.deleteUserAccountById(id);
        } else {
            return R.error();
        }
    }
    @PostMapping("/api/favor/update")
    @CrossOrigin
    public R updateFavor(@RequestBody UserWithFavor userWithFavor) {
        Favor favor = userWithFavor.getFavor();
        favor.setId(userWithFavor.getUserAccount().getId());
        return favorService.updateFavor(favor);
    }
    @PostMapping("/api/user/update")
    @CrossOrigin
    public R updateUser(@RequestBody UserAccount userAccount) {
        return userAccountService.updateUserAccount(userAccount);
    }
//    @CrossOrigin
//    @PostMapping("/api/user/login")
//    public R login(@RequestBody UserAccount userAccount)
//    {
//        return userAccountService.login(userAccount);
//    }
    @CrossOrigin
    @PostMapping("/api/user/joinHollow")
    public R joinHollow(HttpServletRequest request)
    {
        String token=getTokenFromHeader(request);
        UserAccount userAccount = (UserAccount) userAccountService.getRByToken(token).getData();
        if (userAccount==null)
        {
            return R.error();
        }
        userAccount.setHollow(1);
        userAccountService.setToken(token,userAccount);
        return userAccountService.updateUserAccount(userAccount);
    }


    @CrossOrigin
    @PostMapping("/api/user/getByToken")
    public R getUserByToken(HttpServletRequest request)
    {
        return userAccountService.getRByToken(getTokenFromHeader(request));
    }


    @CrossOrigin
    @PostMapping("/api/user/forgetPW")
    public R forgetPW(@RequestBody UserAccount RAccount)//用户传进来的
    {
        UserAccount SAccount =userAccountService.selectUserAccountByUsername(RAccount.getUsername());//SQL中的

        if (SAccount==null)
        {
            return R.error();
        }

        if (RAccount.getSchoolId().equals(SAccount.getSchoolId()))
        {
            if(RAccount.getBirthYear().equals(SAccount.getBirthYear()))
            {
                SAccount.setPassword(RAccount.getPassword());
                return userAccountService.updateUserAccount(SAccount);
            }
        }

        return R.error();
    }

    @CrossOrigin
    @PostMapping("/api/user/changeNickname")
    public R changeNickname(@RequestBody UserAccount userAccount,HttpServletRequest request)
    {
        String token=getTokenFromHeader(request);
        //该userAccount已经更改过nickname
        UserAccount redis= userAccountService.getUserByToken(token);
        redis.setNickname(userAccount.getNickname());
        userAccountService.setToken(token,redis);
        return userAccountService.changeUserNickname(redis);
    }
    @CrossOrigin
    @PostMapping("/api/user/verifyUsername")
    public R verifyUsername(@RequestBody String name)
    {

        name=name.replace("\"","");
        UserAccount saved=userAccountService.selectUserAccountByUsername(name);
        return saved==null?R.success():R.error();

    }

    @CrossOrigin
    @PostMapping("/api/user/login")
    public R login(@RequestBody UserAccount userAccount)
    {
        return userAccountService.Login(userAccount);
    }




    @CrossOrigin
    @PostMapping("/api/user/getList")
    public R getUserList()
    {
        return userAccountService.selectAllUserAccount();
    }



    public String getTokenFromHeader(HttpServletRequest request)
    {
        return request.getHeader("userToken");
    }
}
