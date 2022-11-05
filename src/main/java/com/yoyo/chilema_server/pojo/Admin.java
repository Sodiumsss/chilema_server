package com.yoyo.chilema_server.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/27 22:50
 * @package: com.yoyo.chilema_server.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String password;
}
