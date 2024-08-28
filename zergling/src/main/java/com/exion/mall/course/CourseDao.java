package com.exion.mall.course;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao {
	List<CourseDto> selectCourse();
}
