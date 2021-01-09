package com.halayang.server.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.server.course.mapper.CourseMapper;
import com.halayang.server.course.po.CoursePO;
import com.halayang.server.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CourseMapper courseMapper;

}
