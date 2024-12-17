package com.exion.mall.video;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class VideoDto {
	private String yvSeq;
	private String yvUrl;
	private Integer yvDelNy;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date yvRegDate;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date yvModDate;
	private String pdSeq;
	
	private String title;
	private String intro;
	private Integer publisher;
	private String publisherName;
	private String authors;
	private String path;
//=========================
	public String getYvSeq() {
		return yvSeq;
	}
	public void setYvSeq(String yvSeq) {
		this.yvSeq = yvSeq;
	}
	public String getYvUrl() {
		return yvUrl;
	}
	public void setYvUrl(String yvUrl) {
		this.yvUrl = yvUrl;
	}
	public Integer getYvDelNy() {
		return yvDelNy;
	}
	public void setYvDelNy(Integer yvDelNy) {
		this.yvDelNy = yvDelNy;
	}
	public Date getYvRegDate() {
		return yvRegDate;
	}
	public void setYvRegDate(Date yvRegDate) {
		this.yvRegDate = yvRegDate;
	}
	public Date getYvModDate() {
		return yvModDate;
	}
	public void setYvModDate(Date yvModDate) {
		this.yvModDate = yvModDate;
	}
	public String getPdSeq() {
		return pdSeq;
	}
	public void setPdSeq(String pdSeq) {
		this.pdSeq = pdSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getPublisher() {
		return publisher;
	}
	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	
	
}
