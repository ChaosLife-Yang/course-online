package com.halayang.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/2/7 17:44           1.0
 * @program oauth2-authority
 * @description
 * @create 2021/2/7 17:44
 */
public class ContextHolder {

    public static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    private ContextHolder() {
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(8);
            THREAD_LOCAL.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(8);
            THREAD_LOCAL.set(map);
        }

        return map.get(key);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static void setUserId(String userId) {
        set("userId", userId);
    }

    public static Object getUserId() {
        return get("userId");
    }

    public static void setPrincipal(String claims) {
        Map<String, Object> map = JacksonUtils.toMap(claims, String.class, Object.class);
        set("claims", map);
    }

    public static Object getPrincipal() {
        return get("claims");
    }

}
