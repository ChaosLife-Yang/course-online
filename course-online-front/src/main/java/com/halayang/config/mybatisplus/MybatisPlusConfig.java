package com.halayang.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MybatisPlus配置类
 *
 * @author YangYudi
 * @date 2020/12/14 14:02
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {

    /**
     * MyBatisPlus配置分页
     *
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @author WangMingshuai
     * @date 2020/1/7 10:22
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 使用mp实现添加操作，这个方法执行
     *
     * @param metaObject org.apache.ibatis.reflection.MetaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("editTime", now, metaObject);

        this.setFieldValByName("version", 1L, metaObject);
    }

    /**
     * 使用mp实现修改操作，这个方法执行
     *
     * @param metaObject org.apache.ibatis.reflection.MetaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName("editTime", now, metaObject);
    }

}
