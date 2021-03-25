package com.halayang.server.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.halayang.common.dto.CourseDTO;
import com.halayang.common.dto.PopularAndNewCourseDTO;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.server.course.po.CoursePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-03
 */
public interface CourseService extends IService<CoursePO> {

    /**
     * 根据id获取课程所有大章和小节
     *
     * @param courseId 课程id
     * @return com.halayang.common.vo.CourseWebVo
     * @author YangYudi
     * @date 2021/3/14 11:08
     */
    CourseWebVo getCourseWeb(String courseId);

    /**
     * 热门课程与新上好课
     *
     * @return com.halayang.common.dto.PopularAndNewCourseDTO
     * @author YangYudi
     * @date 2021/3/14 17:58
     */
    PopularAndNewCourseDTO popularAndNewCourse();

    List<CourseDTO> getCourseByCategoryId(String level1, String level2);
}
