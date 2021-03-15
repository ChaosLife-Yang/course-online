package com.halayang.common.vo;

import com.halayang.common.dto.SectionDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/14 11:06           1.0
 * @program course-online
 * @description
 * @create 2021/3/14 11:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "课程章节展示对象", description = "课程章节详情VO展示对象")
public class ChapterWebVo {


    @ApiModelProperty(value = "章节id")
    private String id;

    @ApiModelProperty(value = "章节名")
    private String name;

    @ApiModelProperty(value = "章节下的小节")
    private List<SectionDTO> sections;
}
