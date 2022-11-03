package com.yoyo.chilema_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:17
 * @package: com.yoyo.chilema_server.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private Long foodId;
    private Long userId;
    private String content;
    private float rate;
    private String createTime;
    private int likeNum;
    private int up;

}
