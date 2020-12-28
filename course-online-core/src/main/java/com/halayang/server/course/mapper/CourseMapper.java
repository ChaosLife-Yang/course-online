package com.halayang.server.course.mapper;

import com.halayang.server.course.po.CoursePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

}
