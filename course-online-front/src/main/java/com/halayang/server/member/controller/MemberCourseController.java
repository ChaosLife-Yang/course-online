package com.halayang.server.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.halayang.common.utils.response.ResponseObject;
import com.halayang.common.utils.response.ResponseResult;
import com.halayang.server.member.dto.MemberCourseDTO;
import com.halayang.server.member.po.CoursePO;
import com.halayang.server.member.po.MemberCoursePO;
import com.halayang.server.member.service.CourseService;
import com.halayang.server.member.service.MemberCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户课程 前端控制器
 * </p>
 *
 * @author YangYuDi
 * @since 2021-03-30 09:07:50
 */
@Api(tags = {"会员与课程关联"})
@RestController
@RequestMapping("/memberCourse")
public class MemberCourseController {

    @Autowired
    private MemberCourseService memberCourseService;

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "判断用户是否参加课程", httpMethod = "GET", notes = "判断用户是否参加课程")
    @GetMapping("/course/{courseId}/{memberId}")
    public ResponseObject<String> course(@PathVariable String courseId, @PathVariable String memberId) {
        int count = memberCourseService.count(new LambdaQueryWrapper<MemberCoursePO>()
                .eq(MemberCoursePO::getCourseId, courseId)
                .eq(MemberCoursePO::getMemberId, memberId));
        if (count > 1) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    /**
     * 用户课程添加或更新
     *
     * @param memberCourseDTO 请求参数
     * @return com.halayang.common.utils.response.ResponseObject<java.lang.String>
     * @author YangYudi
     * @date 2021-03-30 09:07:50
     */
    @ApiOperation(value = "用户课程添加或更新", httpMethod = "POST", notes = "用户课程添加或更新")
    @PostMapping("/saveOrUpdate")
    public ResponseObject<String> saveOrUpdate(@RequestBody @Validated MemberCourseDTO memberCourseDTO) {
        MemberCoursePO memberCoursePo = new MemberCoursePO();
        BeanUtils.copyProperties(memberCourseDTO, memberCoursePo);
        memberCoursePo.setAt(LocalDateTime.now());
        boolean option = memberCourseService.saveOrUpdate(memberCoursePo);
        if (option) {
            CoursePO coursePO = courseService.getById(memberCoursePo.getCourseId());
            if (!ObjectUtils.isEmpty(coursePO)) {
                //课程参加人数加一
                CoursePO update = new CoursePO().setEnroll(coursePO.getEnroll() + 1);
                courseService.updateById(update);
            }
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "用户退出课程", httpMethod = "POST", notes = "用户退出课程")
    @PostMapping("/quit")
    public ResponseObject<String> quit(@RequestBody MemberCourseDTO memberCourseDTO) {
        boolean remove = memberCourseService.remove(new LambdaQueryWrapper<MemberCoursePO>()
                .eq(MemberCoursePO::getCourseId, memberCourseDTO.getCourseId())
                .eq(MemberCoursePO::getMemberId, memberCourseDTO.getMemberId()));
        if (remove) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error();
        }
    }

}
