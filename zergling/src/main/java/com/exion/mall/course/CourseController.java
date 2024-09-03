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
	
	@RequestMapping(value = "/v1/mall/course/courseXdmList")
	public String courseXdm(Model model) {
		model.addAttribute("list", courseService.selectCourse());
		model.addAttribute("totalRows", courseService.selectCourse().size()) ;
		return "xdm/v1/mall/course/courseXdm";
	}
	
	@RequestMapping(value = "/v1/mall/course/courseXdmForm")
	public String courseXdmForm() {
		return "xdm/v1/mall/course/courseXdmForm";
	}
	@RequestMapping(value = "/v1/mall/course/courseXdmInst")
	public String courseXdmInst(CourseDto courseDto) {
		courseService.insert(courseDto);
		return "redirect:/v1/mall/course/courseXdmList";
	}
	@RequestMapping(value = "/v1/mall/course/courseXdmMfom")
	public String courseXdmMfom(CourseDto courseDto,Model model) {
		model.addAttribute("item", courseService.selectOne(courseDto));
		return "xdm/v1/mall/course/courseXdmMfom";
	}
}
