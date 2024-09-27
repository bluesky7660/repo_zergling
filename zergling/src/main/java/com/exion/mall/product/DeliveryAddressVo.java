package com.exion.mall.product;

import com.exion.infra.codegroup.BaseVo;

public class DeliveryAddressVo extends BaseVo{
	private String seq;
	private String daSeq;
	private String member_seq;
	private Integer defaultNy;
	private Integer chSeq;
	private Double longitude;
	private Double latitude;
//---------------------------
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

	public String getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(String member_seq) {
		this.member_seq = member_seq;
	}

	public Integer getDefaultNy() {
		return defaultNy;
	}

	public void setDefaultNy(Integer defaultNy) {
		this.defaultNy = defaultNy;
	}

	public Integer getChSeq() {
		return chSeq;
	}

	public void setChSeq(Integer chSeq) {
		this.chSeq = chSeq;
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
	
}
