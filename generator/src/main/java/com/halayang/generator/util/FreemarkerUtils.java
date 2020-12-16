package com.halayang.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/15 14:37           1.0
 * @program course-online
 * @description
 * @create 2020/12/15 14:37
 */
public class FreemarkerUtils {

    /**
     * 模板所在的父目录
     */
    private static String ftlPath = "generator/src/main/java/com/halayang/generator/ftl/";

    private static Template temp;

    /**
     * 读取模板
     *
     * @param ftlName 模板名称
     * @author YangYudi
     * @date 2020/12/16 10:27
     */
    public static void initConfig(String ftlName) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        File file = new File(ftlPath);
        cfg.setDirectoryForTemplateLoading(file);
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        temp = cfg.getTemplate(ftlName);
    }

    /**
     * 根据模板名称生成文件
     *
     * @param fileName 目标路径
     * @param map      参数集合map
     * @author YangYudi
     * @date 2020/12/16 10:28
     */
    public static void generator(String fileName, Map<String, Object> map) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(map, bw);
        bw.flush();
        fw.close();
    }

}
