package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.FoodComment;

public interface CommentService {
    R addComment(FoodComment foodComment);
}
