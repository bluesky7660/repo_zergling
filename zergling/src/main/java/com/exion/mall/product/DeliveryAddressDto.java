package com.exion.mall.product;

import java.util.Date;


public class DeliveryAddressDto {
	private String daSeq;
	private Integer defaultNy;
	private String addressName;
	private String RecipientName;
	private String RecipientPhone;
	private String daZonecode;
	private String daRoadAddress;
	private String daJibunAddress;
	private String daDetailAddress;
	private String daExtraAddress;
	private Double longitude;
	private Double latitude;
	private Date regDate;
	private Date modDate;
	private Integer delNy;
	private String member_seq;
	
	private String seq;
	private String name;
//-------------------------------------------
	
	public String getDaSeq() {
		return daSeq;
	}
	public void setDaSeq(String daSeq) {
		this.daSeq = daSeq;
	}
	public Integer getDefaultNy() {
		return defaultNy;
	}
	public void setDefaultNy(Integer defaultNy) {
		this.defaultNy = defaultNy;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getRecipientName() {
		return RecipientName;
	}
	public void setRecipientName(String recipientName) {
		RecipientName = recipientName;
	}
	public String getRecipientPhone() {
		return RecipientPhone;
	}
	public void setRecipientPhone(String recipientPhone) {
		RecipientPhone = recipientPhone;
	}
	
	public String getDaZonecode() {
		return daZonecode;
	}
	public void setDaZonecode(String daZonecode) {
		this.daZonecode = daZonecode;
	}
	public String getDaRoadAddress() {
		return daRoadAddress;
	}
	public void setDaRoadAddress(String daRoadAddress) {
		this.daRoadAddress = daRoadAddress;
	}
	public String getDaJibunAddress() {
		return daJibunAddress;
	}
	public void setDaJibunAddress(String daJibunAddress) {
		this.daJibunAddress = daJibunAddress;
	}
	public String getDaDetailAddress() {
		return daDetailAddress;
	}
	public void setDaDetailAddress(String daDetailAddress) {
		this.daDetailAddress = daDetailAddress;
	}
	public String getDaExtraAddress() {
		return daExtraAddress;
	}
	public void setDaExtraAddress(String daExtraAddress) {
		this.daExtraAddress = daExtraAddress;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
