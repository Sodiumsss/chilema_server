package com.yoyo.chilema_server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class
UserAccount {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    private Integer schoolId;
    private Integer birthYear;
    private String nickname;
    private Integer credit;
    private boolean hollow;
    private Integer sex;

}
