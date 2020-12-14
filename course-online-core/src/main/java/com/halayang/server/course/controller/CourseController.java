package com.halayang.server.course.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2020-12-03
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
