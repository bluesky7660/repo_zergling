package com.exion.mall.product;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderDto {
	private String uoSeq;
	private String orderNumber;
	private Integer orderStatus;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date orderDate;
	private String deliveryDate;
	private Integer uoPrice;
	private Integer uoQuantity;
	private String uoDeliveryReq; 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date uoModDate;
	private Integer delNy;
	private String member_seq;
	private String deliveryAddress_daSeq;
	
	private String product_seq;
	private String seq;
	private String daSeq;
	private String mmSeq;
	private String orderStatusText;
	private String title;
	private String prodTypeName;
	private String imgSrc;
	private String name;
	private String userId;
	
	private Integer orderMonth;
	private Integer orderCount;
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
	public Integer getUoPrice() {
		return uoPrice;
	}
	public void setUoPrice(Integer uoPrice) {
		this.uoPrice = uoPrice;
	}
	public Integer getUoQuantity() {
		return uoQuantity;
	}
	public void setUoQuantity(Integer uoQuantity) {
		this.uoQuantity = uoQuantity;
	}
	public String getUoDeliveryReq() {
		return uoDeliveryReq;
	}
	public void setUoDeliveryReq(String uoDeliveryReq) {
		this.uoDeliveryReq = uoDeliveryReq;
	}
	public Date getUoModDate() {
		return uoModDate;
	}
	public void setUoModDate(Date uoModDate) {
		this.uoModDate = uoModDate;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	public String getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(String member_seq) {
		this.member_seq = member_seq;
	}
	
	public String getDeliveryAddress_daSeq() {
		return deliveryAddress_daSeq;
	}
	public void setDeliveryAddress_daSeq(String deliveryAddress_daSeq) {
		this.deliveryAddress_daSeq = deliveryAddress_daSeq;
	}
	public String getProduct_seq() {
		return product_seq;
	}
	public void setProduct_seq(String product_seq) {
		this.product_seq = product_seq;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getDaSeq() {
		return daSeq;
	}
	public void setDaSeq(String daSeq) {
		this.daSeq = daSeq;
	}
	public String getMmSeq() {
		return mmSeq;
	}
	public void setMmSeq(String mmSeq) {
		this.mmSeq = mmSeq;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getOrderStatusText() {
		return orderStatusText;
	}
	public void setOrderStatusText(String orderStatusText) {
		this.orderStatusText = orderStatusText;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProdTypeName() {
		return prodTypeName;
	}
	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getOrderMonth() {
		return orderMonth;
	}
	public void setOrderMonth(Integer orderMonth) {
		this.orderMonth = orderMonth;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	
}
