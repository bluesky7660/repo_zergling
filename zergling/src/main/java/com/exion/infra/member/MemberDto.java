package com.exion.infra.member;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class MemberDto {
	private String seq;
	private Integer admNy;
	private String name;
	private String userId;
	private String userPassword;
	private Integer gender;
	private String codeName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
//	private String dateOfBirth;
	private String email;
	private String phoneNum;
	private String userDesc;
	private Date regDate;
	private Date modDate;
	private Integer delNy;
	private Integer kakaoLoginNy;
//--------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Integer getAdmNy() {
		return admNy;
	}
	public void setAdmNy(Integer admNy) {
		this.admNy = admNy;
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
//	public String getDateOfBirth() {
//	return dateOfBirth;
//}
//public void setDateOfBirth(String dateOfBirth) {
//	this.dateOfBirth = dateOfBirth;
//}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
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
	public Integer getKakaoLoginNy() {
		return kakaoLoginNy;
	}
	public void setKakaoLoginNy(Integer kakaoLoginNy) {
		this.kakaoLoginNy = kakaoLoginNy;
	}
	
}
