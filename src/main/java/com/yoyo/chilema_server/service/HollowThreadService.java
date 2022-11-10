package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.HollowReply;
import com.yoyo.chilema_server.pojo.HollowThread;
import com.yoyo.chilema_server.pojo.noSQL.UserHollowText;

import java.util.List;

public interface HollowThreadService {
    R post(HollowThread hollowThread);
    R getHollowByDesc(Integer page);
    R getSingleHollow(Long tid,Long userId);
    R deleteMyselfById(Long id);
    R edit(HollowThread hollowThread);
    R setLike(Long tid,Long userId);
    R cancelLike(Long tid,Long userId);
    List<HollowReply> getReplies(Long tid);
    R reply(UserHollowText userHollowText);

}
