package com.exion.mall.restaurant;



import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RestaurantDto {
	private String seq;
	private String foodType;
	private String name;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime businessHoursStart;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime businessHoursEnd;
	private String tel;
	private String webAddress;
	private Integer parkingNy;
	private Integer bookingNy;
	private Integer freeCorkageNy;
	private String intro;
	private Date regDate;
	private Date modDate;
	private Integer delNy;
//------------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalTime getBusinessHoursStart() {
		return businessHoursStart;
	}
	public void setBusinessHoursStart(LocalTime businessHoursStart) {
		this.businessHoursStart = businessHoursStart;
	}
	public LocalTime getBusinessHoursEnd() {
		return businessHoursEnd;
	}
	public void setBusinessHoursEnd(LocalTime businessHoursEnd) {
		this.businessHoursEnd = businessHoursEnd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWebAddress() {
		return webAddress;
	}
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	public Integer getParkingNy() {
		return parkingNy;
	}
	public void setParkingNy(Integer parkingNy) {
		this.parkingNy = parkingNy;
	}
	public Integer getBookingNy() {
		return bookingNy;
	}
	public void setBookingNy(Integer bookingNy) {
		this.bookingNy = bookingNy;
	}
	public Integer getFreeCorkageNy() {
		return freeCorkageNy;
	}
	public void setFreeCorkageNy(Integer freeCorkageNy) {
		this.freeCorkageNy = freeCorkageNy;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	
}
