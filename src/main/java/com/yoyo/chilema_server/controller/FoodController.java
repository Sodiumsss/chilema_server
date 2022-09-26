package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:41
 * @package: com.yoyo.chilema_server.controller
 * @Version: 1.0
 */
@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/api/food/test")
    public R test() {
        return R.success("hello2");
    }
}
