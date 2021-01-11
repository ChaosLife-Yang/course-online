package com.halayang.server.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.server.course.mapper.CourseMapper;
import com.halayang.server.course.mapper.SectionMapper;
import com.halayang.server.course.po.SectionPO;
import com.halayang.server.course.service.SectionService;
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
        int count = this.count(new LambdaQueryWrapper<SectionPO>().eq(SectionPO::getCourseId, id));
        if (count > 0) {
            courseMapper.updateCourseTime(id);
        }
    }

}
