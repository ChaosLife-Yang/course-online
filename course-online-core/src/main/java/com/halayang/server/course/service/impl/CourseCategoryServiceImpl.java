package com.halayang.server.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.halayang.server.course.dto.CourseCategoryDTO;
import com.halayang.server.course.mapper.CourseCategoryMapper;
import com.halayang.server.course.po.CourseCategoryPO;
import com.halayang.server.course.service.CourseCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15
 */
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategoryPO> implements CourseCategoryService {

    @Override
    public boolean saveOrUpdateCourseCategories(CourseCategoryDTO courseCategoryDTO) {
        int count = this.count(new LambdaQueryWrapper<CourseCategoryPO>()
                .eq(CourseCategoryPO::getCourseId, courseCategoryDTO.getCourseId()));
        if (count > 0) {
            return updateCourseCategories(courseCategoryDTO);
        }
        return addCourseCategories(courseCategoryDTO);
    }

    private boolean addCourseCategories(CourseCategoryDTO courseCategoryDTO) {

        if (!CollectionUtils.isEmpty(courseCategoryDTO.getCategoryId())) {
            List<CourseCategoryPO> collect = courseCategoryDTO.getCategoryId().stream()
                    .map(s -> new CourseCategoryPO()
                            .setCourseId(courseCategoryDTO.getCourseId())
                            .setCategoryId(s))
                    .collect(Collectors.toList());
            return this.saveBatch(collect);
        }
        return true;
    }

    private boolean updateCourseCategories(CourseCategoryDTO courseCategoryDTO) {
        //先删再加
        return this.remove(new LambdaQueryWrapper<CourseCategoryPO>()
                .eq(CourseCategoryPO::getCourseId, courseCategoryDTO.getCourseId())) &&
                this.addCourseCategories(courseCategoryDTO);
    }

}
