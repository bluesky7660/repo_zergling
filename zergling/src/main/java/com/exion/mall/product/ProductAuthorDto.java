package com.exion.mall.product;

import java.util.List;

public class ProductAuthorDto {
	private String paSeq;
	private String product_seq;
//	private List<String> author_seq;
	private List<String> listAuthor_seq;
	private String author_seq;
	private String seq;
	private String name;
	private Integer aDefaultNy;
	private Integer pDefaultNy;
	private Integer delNy;
//------------------------------
	public String getPaSeq() {
		return paSeq;
	}
	public void setPaSeq(String paSeq) {
		this.paSeq = paSeq;
	}
	public String getProduct_seq() {
		return product_seq;
	}
	public void setProduct_seq(String product_seq) {
		this.product_seq = product_seq;
	}
	public String getAuthor_seq() {
		return author_seq;
	}
	public void setAuthor_seq(String author_seq) {
		this.author_seq = author_seq;
	}
//	public List<String> getAuthor_seq() {
//		return author_seq;
//	}
//	public void setAuthor_seq(List<String> author_seq) {
//		this.author_seq = author_seq;
//	}

	public List<String> getListAuthor_seq() {
		return listAuthor_seq;
	}
	public void setListAuthor_seq(List<String> listAuthor_seq) {
		this.listAuthor_seq = listAuthor_seq;
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
	public Integer getaDefaultNy() {
		return aDefaultNy;
	}
	public void setaDefaultNy(Integer aDefaultNy) {
		this.aDefaultNy = aDefaultNy;
	}
	public Integer getpDefaultNy() {
		return pDefaultNy;
	}
	public void setpDefaultNy(Integer pDefaultNy) {
		this.pDefaultNy = pDefaultNy;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	
}
