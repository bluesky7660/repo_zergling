package com.exion.mall.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value = "/v1/mall/course/courseXdm")
	public String courseXdm() {
		List<CourseDto> courses = courseService.selectCourse();
		for(CourseDto course: courses) {
			System.out.printf("|%-5s|%-7s|%-30s|\n",course.getSeq(),course.getEduType(),course.getCourseName());
		}
		return "xdm/v1/mall/course/courseXdm";
	}
}
