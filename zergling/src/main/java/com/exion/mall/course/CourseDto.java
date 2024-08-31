package com.exion.mall.course;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseDto {
	private String seq;
	private String eduType;
	private String courseName;
	private Integer coursePrice;
	private String  instructor;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date courseRegStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date courseRegEnd;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date studyStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date studyEnd;
	private String location;
	private String content;
	private Date regDate;
	private Date modDate;
	private Integer delNy;
//-------------------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getEduType() {
		return eduType;
	}
	public void setEduType(String eduType) {
		this.eduType = eduType;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public Date getCourseRegStart() {
		return courseRegStart;
	}
	public void setCourseRegStart(Date courseRegStart) {
		this.courseRegStart = courseRegStart;
	}
	public Date getCourseRegEnd() {
		return courseRegEnd;
	}
	public void setCourseRegEnd(Date courseRegEnd) {
		this.courseRegEnd = courseRegEnd;
	}
	public Date getStudyStart() {
		return studyStart;
	}
	public void setStudyStart(Date studyStart) {
		this.studyStart = studyStart;
	}
	public Date getStudyEnd() {
		return studyEnd;
	}
	public void setStudyEnd(Date studyEnd) {
		this.studyEnd = studyEnd;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	
}
