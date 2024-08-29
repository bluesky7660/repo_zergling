package com.exion.mall.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value = "/v1/mall/course/courseXdm")
	public String courseXdm(Model model) {
		model.addAttribute("list", courseService.selectCourse());
		model.addAttribute("totalRows", courseService.selectCourse().size()) ;
		return "xdm/v1/mall/course/courseXdm";
	}
}
