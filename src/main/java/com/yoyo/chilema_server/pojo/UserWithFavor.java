package com.yoyo.chilema_server.pojo;

import lombok.Data;

@Data
public class UserWithFavor {
    private Favor favor;
    private UserAccount userAccount;
}
