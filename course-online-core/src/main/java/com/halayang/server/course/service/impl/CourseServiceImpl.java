package com.halayang.server.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.common.dto.CategoryCourseSearchDto;
import com.halayang.common.dto.CourseDTO;
import com.halayang.common.dto.PopularAndNewCourseDTO;
import com.halayang.common.enums.CourseStatusEnum;
import com.halayang.common.utils.CopyUtils;
import com.halayang.common.vo.CourseWebVo;
import com.halayang.server.course.mapper.CourseMapper;
import com.halayang.server.course.po.CoursePO;
import com.halayang.server.course.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CoursePO> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public CourseWebVo getCourseWeb(String courseId) {
        CoursePO coursePO = this.getById(courseId);
        CourseDTO courseDTO = CopyUtils.copy(coursePO, CourseDTO.class);

        return new CourseWebVo()
                .setCourse(courseDTO)
                .setChapterList(courseMapper.getCourseChapterWeb(courseId));
    }

    /**
     * 热门课程和新课程列表展示
     *
     * @return com.halayang.server.front.dto.PopularAndNewCourseDTO
     * @author YangYudi
     * @date 2021/3/12 17:40
     */
    @Override
    public PopularAndNewCourseDTO popularAndNewCourse() {
        List<CoursePO> newPoList = this.list(new LambdaQueryWrapper<CoursePO>()
                .eq(CoursePO::getStatus, CourseStatusEnum.PUBLISH.getCode())
                .orderByDesc(CoursePO::getCreateTime)
                .last("limit 8"));
        List<CoursePO> popularPoList = this.list(new LambdaQueryWrapper<CoursePO>()
                .eq(CoursePO::getStatus, CourseStatusEnum.PUBLISH.getCode())
                .orderByDesc(CoursePO::getEnroll)
                .last("limit 8"));

        return new PopularAndNewCourseDTO()
                .setPopular(CopyUtils.copyList(popularPoList, CourseDTO.class))
                .setFresh(CopyUtils.copyList(newPoList, CourseDTO.class));
    }

    @Override
    public List<CourseDTO> getCourseByCategoryId(String level1, String level2) {
        return courseMapper.getCourseByCategoryId(level1, level2);
    }
}
