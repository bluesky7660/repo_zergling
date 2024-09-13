package com.exion.mall.product;

public class ProductVo {
	private String seq;
	
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