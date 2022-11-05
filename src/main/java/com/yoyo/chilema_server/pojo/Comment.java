package com.yoyo.chilema_server.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long foodId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String content;
    private float rate;
    private String createTime;
    private int likeNum;
    private int up;

}
