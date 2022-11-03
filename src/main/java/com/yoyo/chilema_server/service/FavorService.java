package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.Favor;

public interface FavorService {
    R addFavor(Favor favor);
    R deleteFavorById(Long id);
    R updateFavor(Favor favor);
    R selectFavor();
    R selectFavorById(Favor favor);

}
