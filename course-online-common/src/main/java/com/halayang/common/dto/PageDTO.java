package com.halayang.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private Long page;
    /**
     * 当前页行数
     */
    private Long size;

    /**
     * 共多少页
     */
    private Integer pages;

    /**
     * 数据
     */
    private List<T> list;
}
