package com.halayang.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2020/12/13 12:56           1.0                       分页展示vo
 * @program course-online
 * @description
 * @create 2020/12/13 12:56
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

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


}
