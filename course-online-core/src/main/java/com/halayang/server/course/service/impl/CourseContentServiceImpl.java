package com.halayang.server.course.service.impl;

import com.halayang.server.course.po.CourseContentPO;
import com.halayang.server.course.mapper.CourseContentMapper;
import com.halayang.server.course.service.CourseContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程内容 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-09
 */
@Service
public class CourseContentServiceImpl extends ServiceImpl<CourseContentMapper, CourseContentPO> implements CourseContentService {

}
