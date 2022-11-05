package com.yoyo.chilema_server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String password;
    private Integer schoolId;
    private Integer birthYear;
    private String nickname;
    private Integer credit;
    private Integer hollow;
    private Integer sex;

    public Boolean equal(UserAccount another)
    {//比对用户名，密码，昵称
        if (this.username.equals(another.getUsername()))
        {
            if (this.password.equals(another.getPassword()))
            {
                return this.nickname.equals(another.getNickname());
            }
        }
        return false;
    }

    public void clearSensitiveness()
    {
        this.password=null;
        this.schoolId=null;
        this.birthYear=null;
    }

}
