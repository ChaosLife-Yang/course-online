package com.halayang.server.section.service.impl;

import com.halayang.server.course.mapper.CourseMapper;
import com.halayang.server.section.po.SectionPO;
import com.halayang.server.section.mapper.SectionMapper;
import com.halayang.server.section.service.SectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 小节 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15
 */
@Service
public class SectionServiceImpl extends ServiceImpl<SectionMapper, SectionPO> implements SectionService {

    @Autowired
    private CourseMapper courseMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCourseTime(String id) {
        courseMapper.updateCourseTime(id);
    }

}
