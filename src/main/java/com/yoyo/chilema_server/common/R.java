package com.yoyo.chilema_server.common;

import lombok.Data;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/22 16:42
 * @package: com.yoyo.chilema_server.common
 * @Version: 1.0
 */
@Data
public class R {
    private int code;
    private String message;
    private Object data;

    public static R success() {
        R r = new R();
        r.setCode(1);
        return r;
    }
    public static R success(String message) {
        R r = new R();
        r.setCode(1);
        r.setMessage(message);
        return r;
    }

    public static R success(String message,Object data) {
        R r = success(message);
        r.setData(data);
        return r;
    }
    public static R success(Object data) {
        return success(null,data);
    }

    public static R error(String message) {
        R r = success(message);
        r.setCode(0);
        return r;
    }

    public static R error() {
        R r = success();
        r.setCode(0);
        return r;
    }

}