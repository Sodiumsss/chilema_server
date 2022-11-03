package com.yoyo.chilema_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:27
 * @package: com.yoyo.chilema_server.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private Long id;
    private String name;
    private int acid;
    private int sweet;
    private int spicy;
    private int pepper;
    private int oil;
    private String supplyTime;
    private String pic;
    private String description;
}
