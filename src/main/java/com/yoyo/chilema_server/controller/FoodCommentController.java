package com.yoyo.chilema_server.controller;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.FoodComment;
import com.yoyo.chilema_server.service.FoodCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @CrossOrigin
    @PostMapping("/api/comment/add")
    public R addComment(@RequestBody FoodComment foodComment, HttpServletRequest request) {
        return foodCommentService.addComment(foodComment,request);
    }
    @CrossOrigin
    @PostMapping("/api/comment/deleteMyself")
    public R addComment(@RequestBody Long foodId ,HttpServletRequest request) {
        return foodCommentService.deleteMyselfComment(foodId,request);
    }
    @CrossOrigin
    @PostMapping("/api/comment/get")
    public R getCommentList(@RequestBody Long foodId)
    {
        return foodCommentService.getCommentList(foodId);
    }


}
