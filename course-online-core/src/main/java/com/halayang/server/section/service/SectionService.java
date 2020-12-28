package com.halayang.server.section.service;

import com.halayang.server.section.po.SectionPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 小节 服务类
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-15
 */
public interface SectionService extends IService<SectionPO> {

    /**
     * 根据课程id更新课程下面的视频总时长
     *
     * @param id 课程id
     * @author YangYudi
     * @date 2020/12/28 10:54
     * @return int
     */
    void updateCourseTime(String id);

}
