package com.exion.mall.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	@Autowired
	CourseDao courseDao;
	
	public List<CourseDto> selectCourse(){
		List<CourseDto> courses = courseDao.selectCourse();
		return courses;
	}
}
