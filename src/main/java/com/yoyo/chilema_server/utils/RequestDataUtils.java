package com.yoyo.chilema_server.utils;

import com.alibaba.fastjson2.JSONObject;

import java.util.Base64;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/23 10:16
 * @package: com.yoyo.chilema_server.utils
 * @Version: 1.0
 */
public class RequestDataUtils {
    public static <T> T decodeInfo(String info,Class<T> tClass) {
        byte[] decodeBase64Info = Base64.getDecoder().decode(info);
        return JSONObject.parseObject(new String(decodeBase64Info),tClass);
    }

}
