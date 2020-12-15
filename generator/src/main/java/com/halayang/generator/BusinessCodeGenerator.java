package com.halayang.generator;

import com.halayang.generator.util.FreemarkerUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/15 14:48           1.0
 * @program course-online
 * @description
 * @create 2020/12/15 14:48
 */
public class BusinessCodeGenerator {
    /**
     * 要生成的路径文件夹
     */
    private static String toPath = "course-online-core/src/main/java/com/halayang/server/";

    public static void main(String[] args) throws IOException, TemplateException {
        FreemarkerUtils.initConfig("controller.ftl");
        //需要改
        String classNamePrefix = "Course";
        //需要改
        String module = "course";
        toPath += module + "/controller/";
        Map<String, Object> map = new HashMap<>();
        //需要改
        map.put("moduleName", "课程");
        //需要改
        map.put("domain", "course");
        map.put("module", module);
        map.put("classNamePrefix", classNamePrefix);
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        FreemarkerUtils.generator(toPath + classNamePrefix + "Controller.java", map);
    }

}
