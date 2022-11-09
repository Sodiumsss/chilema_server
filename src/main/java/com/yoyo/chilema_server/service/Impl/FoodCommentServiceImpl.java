package com.yoyo.chilema_server.service.Impl;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.mapper.FoodCommentMapper;
import com.yoyo.chilema_server.pojo.FoodComment;
import com.yoyo.chilema_server.service.FoodCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:32
 * @package: com.yoyo.chilema_server.service.Impl
 * @Version: 1.0
 */
@Service
public class FoodCommentServiceImpl implements FoodCommentService {

    @Autowired
    private FoodCommentMapper foodCommentMapper;

    @Override
    public R addComment(FoodComment foodComment) {
        if(foodCommentMapper.insert(foodComment) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }
}