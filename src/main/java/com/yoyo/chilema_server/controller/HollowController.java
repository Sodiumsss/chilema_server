package com.yoyo.chilema_server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.HollowThread;
import com.yoyo.chilema_server.service.HollowThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HollowController {
    @Autowired
    HollowThreadService hollowThreadService;

    @CrossOrigin
    @PostMapping("/api/hollow/post")
    R post(@RequestBody HollowThread hollowThread)
    {
        System.out.println(hollowThread);
        return hollowThreadService.post(hollowThread);
    }

    @CrossOrigin
    @PostMapping("/api/hollow/get")
    R getHollowThreads(@RequestBody Integer page)
    {
        return hollowThreadService.get(page);
    }


}
