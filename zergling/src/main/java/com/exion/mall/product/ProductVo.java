package com.exion.mall.product;

import java.util.Date;

import com.exion.infra.codegroup.BaseVo;

public class ProductVo extends BaseVo{
	private String seq;
	private Integer prodType;
	
	private Date makeDate;
	private Date shipDate;
	private Integer bestNy;
	private Integer todayPickNy;

	
	
	//제조날짜
	private Integer makeDateFillter;
	
	//가격검색
    private Integer minPrice;
    private Integer maxPrice;

    //리뷰점수검색
    private Double minReview;
    private Double maxReview; 
	
    public ProductVo() {
        this.minPrice = 0; // 기본값 설정
        this.maxPrice = 50000; // 기본값 설정
        this.minReview = 0.; // 기본값 설정
        this.maxReview = 10.; // 기본값 설정
    }
    
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public Integer getProdType() {
		return prodType;
	}

	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}

	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Integer getBestNy() {
		return bestNy;
	}

	public void setBestNy(Integer bestNy) {
		this.bestNy = bestNy;
	}

	public Integer getTodayPickNy() {
		return todayPickNy;
	}

	public void setTodayPickNy(Integer todayPickNy) {
		this.todayPickNy = todayPickNy;
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
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinReview() {
		return minReview;
	}

	public void setMinReview(Double minReview) {
		this.minReview = minReview;
	}

	public Double getMaxReview() {
		return maxReview;
	}

	public void setMaxReview(Double maxReview) {
		this.maxReview = maxReview;
	}
	
}
