package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.Favor;
import com.yoyo.chilema_server.pojo.UserAccount;
import org.springframework.stereotype.Service;

public interface FavorService {
    R addFavor(Favor favor);
    R deleteFavor(int id);
    R updateFavor(Favor favor);
    R selectFavor();
    R selectFavorById(int id);

    R deleteFavorByUN(String username);
}
