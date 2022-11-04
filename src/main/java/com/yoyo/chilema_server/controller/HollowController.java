package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.service.HollowThreadService;
import com.yoyo.chilema_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HollowController {
    @Autowired
    HollowThreadService hollowThreadService;

    @CrossOrigin
    @GetMapping("/1")
    void test()
    {
        hollowThreadService.test();
    }


}
