package com.halayang.feign.factory;

import com.halayang.feign.CourseCoreFeign;
import com.halayang.feign.fallback.CourseCoreFeignFallback;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/3/14 13:40           1.0
 * @program course-online
 * @description
 * @create 2021/3/14 13:40
 */
@Component
@Slf4j
public class CourseCoreFeignFallbackFactory implements FallbackFactory<CourseCoreFeign> {
    private final CourseCoreFeignFallback courseCoreFeignFallback;

    public CourseCoreFeignFallbackFactory(CourseCoreFeignFallback courseCoreFeignFallback) {
        this.courseCoreFeignFallback = courseCoreFeignFallback;
    }

    @Override
    public CourseCoreFeign create(Throwable throwable) {
        log.error(throwable.getMessage());
        return courseCoreFeignFallback;
    }
}
