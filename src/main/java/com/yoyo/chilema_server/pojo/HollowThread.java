package com.yoyo.chilema_server.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HollowThread {

    @TableId(type = IdType.AUTO)
    private Long id;//帖子ID
    private Long userId;//user_name表中id作为外键
    private String senderName;//发送人的名字(该字段可以由user更改，但不等同于nickname)
    private String title;//标题
    private String text;//纯文本
    private Integer click;//点击数
    private Integer edit;//是否被sender修改过
    private Integer reply;//回复数
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更改时间(有人回复就会更新)

}
