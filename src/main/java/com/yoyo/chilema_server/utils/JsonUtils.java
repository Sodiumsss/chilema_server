package com.yoyo.chilema_server.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * @description: TODO
 * @Author: Shiro
 * @date: 2022/9/23 11:28
 * @package: com.yoyo.chilema_server.utils
 * @Version: 1.0
 */
public class JsonUtils {
    public static String objToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T jsonToObj(String str,Class<T> tClass) {
        return JSON.parseObject(str,tClass);
    }

    public static Object getJsonObj(String str,String key) {
        JSONObject jsonObject = JSONObject.parseObject(str);
        return jsonObject.get(key);
    }
}
