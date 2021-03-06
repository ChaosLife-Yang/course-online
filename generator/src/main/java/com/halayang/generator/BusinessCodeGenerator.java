package com.halayang.generator;

import com.halayang.generator.util.DbUtil;
import com.halayang.generator.util.Field;
import com.halayang.generator.util.FreemarkerUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
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
     * controller代码生成器
     *
     * @param toPath          要生成的文件所在的父目录
     * @param classNamePrefix 类名前缀
     * @param module          模块名
     * @param domain          对象名
     * @param moduleName      业务中文名
     * @author YangYudi
     * @date 2020/12/16 10:21
     */
    public static void controllerGenerator(String toPath, String classNamePrefix, String module, String domain, String moduleName) throws IOException, TemplateException {
        FreemarkerUtils.initConfig("controller.ftl");
        //需要改
        toPath += module + "/controller/";
        Map<String, Object> map = new HashMap<>();
        map.put("moduleName", moduleName);
        map.put("domain", domain);
        map.put("module", module);
        map.put("classNamePrefix", classNamePrefix);
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        FreemarkerUtils.generator(toPath + classNamePrefix + "Controller.java", map);
    }

    /**
     * dto代码生成器
     *
     * @param toPath          要生成的文件所在的父目录
     * @param classNamePrefix 类名前缀
     * @param module          模块名
     * @param tableName       数据库表名
     * @param moduleName      业务中文名
     * @author YangYudi
     * @date 2020/12/16 11:12
     */
    public static void dtoGenerator(String toPath, String classNamePrefix, String module, String tableName, String moduleName) throws Exception {
        FreemarkerUtils.initConfig("dto.ftl");
        //需要改
        toPath += module + "/dto/";
        List<Field> fields = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fields);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", classNamePrefix);
        map.put("fieldList", fields);
        map.put("typeSet", typeSet);
        map.put("moduleName", moduleName);
        map.put("module", module);
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        FreemarkerUtils.generator(toPath + classNamePrefix + "DTO.java", map);
    }

    /**
     * 获取java属性
     *
     * @param fieldList 属性list
     * @return java.util.Set<java.lang.String>
     * @author YangYudi
     * @date 2020/12/16 10:44
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }

    /**
     * vue页面生成器
     *
     * @param toPath     vue路径
     * @param gateway    网关前缀
     * @param domain     实体名
     * @param tableName  表名
     * @param moduleName 模块中文名
     * @param prefix     前缀
     * @return void
     * @author YangYudi
     * @date 2020/12/28 15:23
     */
    public static void vueGenerator(String toPath, String gateway, String domain, String tableName, String moduleName, String prefix) throws Exception {
        FreemarkerUtils.initConfig("vue.ftl");
        List<Field> fields = DbUtil.getColumnByTableName(tableName);
        Map<String, Object> map = new HashMap<>(4);
        map.put("moduleName", moduleName);
        map.put("fieldList", fields);
        map.put("gateway", gateway);
        map.put("domain", domain);
        FreemarkerUtils.generator(toPath + prefix + ".vue", map);
    }

    public static void main(String[] args) throws Exception {
        String toDtoPath = "course-online-front/src/main/java/com/halayang/server/";
        dtoGenerator(toDtoPath, "Member", "member", "member", "用户");
        dtoGenerator(toDtoPath, "MemberCourse", "member", "member_course", "用户课程");

        String controllerPath = "course-online-front/src/main/java/com/halayang/server/";
        controllerGenerator(controllerPath, "Member", "member", "member", "用户");
        controllerGenerator(controllerPath, "MemberCourse", "member", "memberCourse", "用户课程");

//        String toVuePath = "course-online-ui/course-online-ui-admin/src/views/admin/";
//        vueGenerator(toVuePath, "/api/auth", "role", "role", "角色", "role");
    }

}
