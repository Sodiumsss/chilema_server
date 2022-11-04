package com.yoyo.chilema_server.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
@TableName(autoResultMap = true)
public class Favor {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> taste;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> preference;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> meals;
}
