package com.halayang.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * jackson工具类
 *
 * @author YangYudi
 * @date 2020/12/11 16:30
 */
public class JacksonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private JacksonUtils() {
    }

    /**
     * 将对象转成json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错");
            return null;
        }
    }

    /**
     * 将Json字符串 转成对象
     *
     * @param json   Json字符串
     * @param tClass 类型
     * @param <T>    泛型
     * @return 实体类对象
     */
    public static <T> T toBean(String json, Class<T> tClass) {
        try {
            return MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            logger.error("json解析出错");
            return null;
        }
    }

    /**
     * 将Json字符串转成list
     *
     * @param json   Json字符串
     * @param eClass 类型
     * @param <E>    泛型
     * @return list对象
     */
    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            logger.error("json解析出错");
        }
        return new ArrayList<>();
    }

    /**
     * 将Json字符串转成map
     *
     * @param json   Json字符串
     * @param kClass key类型
     * @param vClass value类型
     * @param <K>    key类型
     * @param <V>    value类型
     * @return map集合
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            logger.error("json解析出错");
            return null;
        }
    }

    /**
     * 将Json字符串泛型的反序列化
     *
     * @param json Json字符串
     * @param type TypeReference类 例如:new TypeReference<List<Integer>>(){} TypeReference类是抽象类
     * @return T
     * @author YangYudi
     * @date 2020/12/11 16:24
     */
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {

            return MAPPER.readValue(json, type);
        } catch (IOException e) {
            logger.error("json解析出错");
            return null;
        }
    }
}
