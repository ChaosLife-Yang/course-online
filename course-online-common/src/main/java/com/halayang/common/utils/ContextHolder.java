package com.halayang.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
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
}
