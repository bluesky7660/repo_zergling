package com.exion.mall.course;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao {
	public List<CourseDto> selectCourse();
	public int insert(CourseDto courseDto);
	public CourseDto selectOne(CourseDto courseDto);
	public int update(CourseDto courseDto);
}
