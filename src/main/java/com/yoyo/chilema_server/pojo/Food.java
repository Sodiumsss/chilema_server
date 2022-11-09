package com.yoyo.chilema_server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/26 22:27
 * @package: com.yoyo.chilema_server.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName(autoResultMap = true)
@AllArgsConstructor
public class Food {
    @TableId(type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;//名称
    private int acid;//酸
    private int sweet;//甜
    private int pepper;//麻
    private int spicy;//辣
    private int oil;//油
    private int salt;//盐
    private Float rate;//评分0~100
    private Float price;//价格
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Boolean> supplyTime;//供应时间
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> supplyLocation;//供应地点
    private String pic;//图片链接
    private String description;//简短描述
}
