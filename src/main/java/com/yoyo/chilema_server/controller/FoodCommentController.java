package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.service.FoodCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:44
 * @package: com.yoyo.chilema_server.controller
 * @Version: 1.0
 */
@RestController
public class FoodCommentController {

    @Autowired
    private FoodCommentService foodCommentService;

    @GetMapping("/api/comment/test")
    public R test() {
        return R.success("hello1");
    }
}
