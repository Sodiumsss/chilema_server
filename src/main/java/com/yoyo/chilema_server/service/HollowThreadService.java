package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.HollowThread;

public interface HollowThreadService {
    R post(HollowThread hollowThread);
    R get(Integer page);
    R deleteById(Long id);
    R edit(HollowThread hollowThread);

}
