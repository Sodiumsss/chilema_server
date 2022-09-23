package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.UserAccount;
import com.yoyo.chilema_server.service.UserAccountService;
import com.yoyo.chilema_server.utils.RequestDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/22 17:38
 * @package: com.yoyo.chilema_server.controller
 * @Version: 1.0
 */
@RestController
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/api/user/create")
    @CrossOrigin
    public R addUser(String info, HttpServletRequest request) {
        UserAccount requestInfo = RequestDataUtils.decodeInfo(info, UserAccount.class);
        System.out.println(requestInfo);
        return userAccountService.addUserAccount(requestInfo);
    }

    @PostMapping("/api/user/create")
    @CrossOrigin
    public R deleteUser(Integer id) {
        return userAccountService.deleteUserAccount(id);
    }
}
