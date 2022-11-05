package com.yoyo.chilema_server.service;

import com.yoyo.chilema_server.common.R;
import com.yoyo.chilema_server.pojo.UserAccount;

public interface UserAccountService {
    R addUserAccount(UserAccount userAccount);
    R deleteUserAccount(UserAccount userAccount);
    R updateUserAccount(UserAccount userAccount);
    R selectAllUserAccount();
    R selectUserAccountById(Long id);
//
//    R login(UserAccount userAccount);


    R Login(UserAccount userAccount);

    UserAccount selectUserAccountByUsername(String username);

    UserAccount selectUserBy3P(UserAccount userAccount);

    R deleteUserAccountByUN(String username);
    R deleteUserAccountById(Long id);
    R changeUserNickname(UserAccount userAccount);
    R getRByToken(String token);
    UserAccount getUserByToken(String token);
    boolean verify(String token);

    void setToken(String token,UserAccount userAccount);
}
