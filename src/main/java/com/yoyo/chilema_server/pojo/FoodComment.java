package com.yoyo.chilema_server.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
public class FoodComment {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;//评论id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long foodId;//食物id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;//用户id
    private String content;//评论内容
    private Float rate;//个人评分，注意与food表中的评分区分开


    private Integer likeNum;//赞同数
    private String senderName;//发送人的名字(该字段可以由user更改，但不等同于nickname)
    private Integer up;//是否被顶置+顶置位序

}
