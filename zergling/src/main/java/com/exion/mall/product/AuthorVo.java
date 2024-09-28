package com.exion.mall.product;

import java.util.Date;
import java.util.List;

import com.exion.infra.codegroup.BaseVo;

public class AuthorVo extends BaseVo{
	private String seq;
	private String prodTypeName;
	private Integer authorType;
	private String name;
	
	private List<String> titleList;
//--------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getProdTypeName() {
		return prodTypeName;
	}
	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public Integer getAuthorType() {
		return authorType;
	}
	public void setAuthorType(Integer authorType) {
		this.authorType = authorType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}
	
	
}
