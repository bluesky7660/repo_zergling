package com.exion.mall.product;

import java.util.Date;

import com.exion.infra.codegroup.BaseVo;

public class ProductVo extends BaseVo{
	private String seq;
	private String prodTypeName;
	private Integer prodType;
	private Integer SlProdType;
	private String title;
	private String name;
	private Integer publisher;
	private String publisherName;
	private Integer reviewNum;
	private Date makeDate;
	private Date shipDate;
	private Integer bestNy;
	private Integer todayPickNy;
	private Integer mdPickNy;
	private Integer freeShippingNy;
	private Integer taxDeductionNy;
	private Integer count;
	
	private Integer newNy;
	
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
//        this.bestNy = 0;
//        this.todayPickNy = 0;
//        this.newNy = 0;
//        this.prodType = 33;
    }
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
	public Integer getProdType() {
		return prodType;
	}
	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}
	public Integer getSlProdType() {
		return SlProdType;
	}
	public void setSlProdType(Integer slProdType) {
		SlProdType = slProdType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPublisher() {
		return publisher;
	}
	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public Integer getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(Integer reviewNum) {
		this.reviewNum = reviewNum;
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
	public Integer getMdPickNy() {
		return mdPickNy;
	}
	public void setMdPickNy(Integer mdPickNy) {
		this.mdPickNy = mdPickNy;
	}
	public Integer getFreeShippingNy() {
		return freeShippingNy;
	}
	public void setFreeShippingNy(Integer freeShippingNy) {
		this.freeShippingNy = freeShippingNy;
	}
	public Integer getTaxDeductionNy() {
		return taxDeductionNy;
	}
	public void setTaxDeductionNy(Integer taxDeductionNy) {
		this.taxDeductionNy = taxDeductionNy;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getNewNy() {
		return newNy;
	}
	public void setNewNy(Integer newNy) {
		this.newNy = newNy;
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
