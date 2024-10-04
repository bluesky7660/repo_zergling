package com.exion.mall.product;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderDto {
	private String uoSeq;
	private String orderNumber;
	private Integer orderStatus;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date orderDate;
	private String name;
	private String member_seq;
	private String seq;
//------------------------------------
	public String getUoSeq() {
		return uoSeq;
	}
	public void setUoSeq(String uoSeq) {
		this.uoSeq = uoSeq;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(String member_seq) {
		this.member_seq = member_seq;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
}
