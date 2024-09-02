package com.exion.mall.product;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductDto {
	private String seq;
	
	private String title;
	private String prodType;
	private String publisher;
	@DateTimeFormat(pattern = "yyyy년 MM월 dd일")
	private Date makeDate;
	private Integer price;
	private Integer sale;
	private Integer salePrice;
	private Integer point;
	private String Intro;
	private Integer reviewNum;
	@DateTimeFormat(pattern = "M/d('E')")
	private Date shipDate;
	private String publisherReview;
	private String preivew;
	private Integer viewNumber;
	private Integer buyNumber;
	private Integer bestNy;
	private Integer todayPickNy;
	private String sliderText;
	private Date regDate;
	private Date modDate;
	private Integer delNy;
}
