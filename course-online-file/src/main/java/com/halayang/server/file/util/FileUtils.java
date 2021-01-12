package com.halayang.server.file.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/12 15:44           1.0
 * @program course-online
 * @description
 * @create 2021/1/12 15:44
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * 获取今日日期
     *
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/8 14:51
     */
    public static String yearFilePrefix() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
    }

    /**
     * 获取今日日期
     *
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/8 14:51
     */
    public static String monthFilePrefix() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    /**
     * 获取今日日期
     *
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/8 14:51
     */
    public static String dayFilePrefix() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
    }

    /**
     * 获得年月日路径
     *
     * @author YangYudi
     * @date 2021/1/12 15:51
     * @return java.lang.String
     */
    public static String getTimePath() {
        return new StringBuilder(FileUtils.yearFilePrefix())
                .append("/")
                .append(FileUtils.monthFilePrefix())
                .append("/")
                .append(FileUtils.dayFilePrefix())
                .append("/")
                .toString();
    }
}
