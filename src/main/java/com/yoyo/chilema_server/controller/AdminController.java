package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.Admin;
import com.yoyo.chilema_server.service.AdminService;
import com.yoyo.chilema_server.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/27 22:57
 * @package: com.yoyo.chilema_server.controller
 * @Version: 1.0
 */
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/api/admin/login")
    @CrossOrigin
    public R login(String loginInfo) {
        System.out.println(loginInfo);
        Admin admin = JsonUtils.jsonToObj(loginInfo, Admin.class);
        System.out.println(admin);
        return adminService.login(admin);
    }
}
