package com.exion.mall.product;

public class ProductVo {
	private String seq;
	private Integer makeDateFillter;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Integer getMakeDateFillter() {
		/*
		 * if (makeDateFillter == null) { makeDateFillter = 1; }
		 */
		return makeDateFillter;
	}
	public void setMakeDateFillter(Integer makeDateFillter) {
		this.makeDateFillter = makeDateFillter;
	}

	
}
