package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.FoodComment;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface FoodCommentService {
    R addComment(FoodComment foodComment, HttpServletRequest request);
    R deleteMyselfComment (Long foodId,HttpServletRequest request);
    R getCommentList(Long id);
}
