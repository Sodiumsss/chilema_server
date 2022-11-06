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

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HollowReply
{
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Long id;//回复id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long replyId;//user_name表中id作为外键
    @JsonSerialize(using = ToStringSerializer.class)
    private Long threadId;//帖子id
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间
    private String text;//纯文本
    private String senderName;//发送人的名字(该字段可以由user更改，但不等同于nickname)
}
