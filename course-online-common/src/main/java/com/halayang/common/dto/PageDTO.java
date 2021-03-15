package com.halayang.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * copyright (C), 2020, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/13 12:56           1.0
 * @program course-online
 * @description
 * @create 2020/12/13 12:56
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {

    /**
     * 当前页
     */
    @NotNull
    @Range(min = 1, message = "页码不小于1")
    private Long page;
    /**
     * 当前页行数
     */
    @NotNull
    @Range(min = 1, message = "每页行数不小于1")
    private Long size;

    /**
     * 共多少页
     */
    @Null
    private Integer pages;

    /**
     * 总条数
     */
    @Null
    private Long total;

    /**
     * 数据
     */
    @Null
    private List<T> list;

    /**
     * 查询条件
     */
    @Null
    private T query;

}
