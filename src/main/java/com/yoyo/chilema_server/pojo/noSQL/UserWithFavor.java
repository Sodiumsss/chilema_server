package com.yoyo.chilema_server.pojo.noSQL;

import com.yoyo.chilema_server.pojo.Favor;
import com.yoyo.chilema_server.pojo.UserAccount;
import lombok.Data;

@Data
public class UserWithFavor {
    private Favor favor;
    private UserAccount userAccount;
}
