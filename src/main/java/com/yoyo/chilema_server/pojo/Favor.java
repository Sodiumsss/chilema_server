package com.yoyo.chilema_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 个人喜好
 * @Author: Shiro
 * @date: 2022/9/23 11:14
 * @package: com.yoyo.chilema_server.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favor {
    private int id;
    private String username;
    private String step1;
    private String step2;
    private String step3;
    private String step4;
}
