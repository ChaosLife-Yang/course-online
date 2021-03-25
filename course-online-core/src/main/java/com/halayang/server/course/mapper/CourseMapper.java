package com.halayang.server.course.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halayang.common.dto.CourseDTO;
import com.halayang.common.vo.ChapterWebVo;
import com.halayang.server.course.po.CoursePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-03
 */
public interface CourseMapper extends BaseMapper<CoursePO> {

    /**
     * 根据课程id更新课程下面的视频总时长
     *
     * @param id 课程id
     * @author YangYudi
     * @date 2020/12/28 10:54
     * @return int
     */
    int updateCourseTime(@Param("id") String id);

    /**
     * 获取课程展示信息
     *
     * @param id 课程id
     * @author YangYudi
     * @date 2021/3/25 11:15
     * @return java.util.List<com.halayang.common.vo.ChapterWebVo>
     */
    List<ChapterWebVo> getCourseChapterWeb(@Param("id") String id);

    /**
     * 根据分类id获取课程列表
     *
     * @param level1 一级分类id
     * @param level2 二级分类id
     * @author YangYudi
     * @date 2021/3/25 11:16
     * @return java.util.List<com.halayang.common.dto.CourseDTO>
     */
    List<CourseDTO> getCourseByCategoryId(@Param("level1") String level1,@Param("level2") String level2);

}
