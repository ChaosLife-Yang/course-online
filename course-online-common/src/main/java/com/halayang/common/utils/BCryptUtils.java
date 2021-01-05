package com.halayang.common.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * BCrypt加密解密工具类
 *
 * @author YUDI
 * @date 2020/7/10
 */
public class BCryptUtils {

    private BCryptUtils() {
    }

    /**
     * 加密
     *
     * @param password 输入密码
     * @return String
     */
    public static String encode(String password) {
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    /**
     * 判断密码正确与否
     *
     * @param password       输入密码
     * @param encodePassword 数据库中已经加密好的密码
     * @return boolean
     */
    public static boolean verify(String password, String encodePassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), encodePassword);
        return result.verified;
    }

    public static void main(String[] args) {
        System.out.println(encode("123456"));
    }

}
