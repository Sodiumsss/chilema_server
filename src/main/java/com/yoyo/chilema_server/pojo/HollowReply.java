package com.yoyo.chilema_server.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HollowReply
{
    private Long id;//回复id
    private Long userId;//user_name表中id作为外键
    private Long threadId;//帖子id
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间
    private String text;//纯文本
    private String senderName;//发送人的名字(该字段可以由user更改，但不等同于nickname)
}
