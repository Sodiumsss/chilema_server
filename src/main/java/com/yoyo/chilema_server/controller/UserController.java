package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.UserWithFavor;
import com.yoyo.chilema_server.pojo.Favor;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.FavorService;
import com.yoyo.chilema_server.service.UserAccountService;
import com.yoyo.chilema_server.utils.RequestDataUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserAccountService userAccountService;

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
    public R login(@RequestBody UserAccount userAccount)
    {
        return userAccountService.login(userAccount);
    }

    @CrossOrigin
    @PostMapping("/api/user/validate")
    public R validate(@RequestBody UserAccount userAccount)
    {
        return userAccountService.validate(userAccount);
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
    @PostMapping("/api/user/getNickname")
    public R getNickname(String info)
    {
        UserAccount userAccount = RequestDataUtils.decodeInfo(info, UserAccount.class);
        return userAccountService.getUserNickname(userAccount);
    }

    @CrossOrigin
    @PostMapping("/api/user/getCredit")
    public R getCredit( @RequestBody UserAccount userAccount)
    {
        return userAccountService.getUserCredit(userAccount);
    }
    @CrossOrigin
    @PostMapping("/api/user/verifyUsername")
    public R verifyUsername(@RequestBody UserAccount userAccount)
    {
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

    @CrossOrigin
    @PostMapping("/api/user/changeNickname")
    public R changeNickname(@RequestBody UserAccount userAccount)
    {
        return userAccountService.changeUserNickname(userAccount);
    }

    @CrossOrigin
    @PostMapping("/api/user/getList")
    public R getUserList()
    {
        return userAccountService.selectAllUserAccount();
    }

    @CrossOrigin
    @PostMapping("/api/user/test")
    public R test(@RequestBody UserAccount userAccount)
    {
        System.out.println(userAccount);
        return R.success("666");
    }
}
