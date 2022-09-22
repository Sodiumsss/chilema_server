package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.UserAccount;

public interface UserAccountService {
    R addUserAccount(UserAccount userAccount);
    R deleteUserAccount(int id);
    R updateUserAccount(UserAccount userAccount);
    R selectAllUserAccount();
    R selectUserAccountById(int id);
}
