package com.exion.infra.login;

public class LoginDto {
	private String seq;
	private Integer admNy;
	private String name;
	private String userId;
	private String userPassword;
//----------------------------------
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
	
}
