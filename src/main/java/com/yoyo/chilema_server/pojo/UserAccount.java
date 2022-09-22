package com.yoyo.chilema_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户账号实体类
 * @Author: Shiro
 * @date: 2022/9/22 15:34
 * @package: com.yoyo.chilema_server.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    private int id;
    private String username;
    private String password;
    private int schoolId;
    private int birthYear;
}
