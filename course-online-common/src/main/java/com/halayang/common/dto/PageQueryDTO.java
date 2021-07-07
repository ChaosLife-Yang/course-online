package com.halayang.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/13 12:56           1.0                        分页查dto
 * @program course-online
 * @description
 * @create 2020/12/13 12:56
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageQueryDTO<T> {

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
     * 查询条件
     */
    @Null
    private T query;

}
