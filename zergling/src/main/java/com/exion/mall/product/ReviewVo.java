package com.exion.mall.product;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.exion.infra.codegroup.BaseVo;

public class ReviewVo extends BaseVo{
	private String rvSeq;
	private Double rvScore;
    private String rvContent;
    private Integer rvSelectTag;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date rvRegDate;
    private Integer delNy;
    private String product_seq;
    private String member_seq;
    
    private String seq;
    private String rvName;
    private String rvSelectTagName;
//----------------------------------------------------
	public String getRvSeq() {
		return rvSeq;
	}
	public void setRvSeq(String rvSeq) {
		this.rvSeq = rvSeq;
	}
	public Double getRvScore() {
		return rvScore;
	}
	public void setRvScore(Double rvScore) {
		this.rvScore = rvScore;
	}
	public String getRvContent() {
		return rvContent;
	}
	public void setRvContent(String rvContent) {
		this.rvContent = rvContent;
	}
	public Integer getRvSelectTag() {
		return rvSelectTag;
	}
	public void setRvSelectTag(Integer rvSelectTag) {
		this.rvSelectTag = rvSelectTag;
	}
	public Date getRvRegDate() {
		return rvRegDate;
	}
	public void setRvRegDate(Date rvRegDate) {
		this.rvRegDate = rvRegDate;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	public String getProduct_seq() {
		return product_seq;
	}
	public void setProduct_seq(String product_seq) {
		this.product_seq = product_seq;
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
	public String getRvName() {
		return rvName;
	}
	public String getRvSelectTagName() {
		return rvSelectTagName;
	}
	
    
}
