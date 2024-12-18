package com.exion.mall.product;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.exion.common.base.FileDto;

public class ProductDto extends FileDto{
	private String seq;
	private String title;
	private Integer prodType;
	private Integer publisher;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date makeDate;
	private Integer price;
	private Integer sale;
	private Integer salePrice;
	private Integer point;
	private Integer stockQty;
	private String imgSrc;
	private String intro;
	private Double reviewNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date shipDate;
	private String tableOfContents;
	private String publisherReview;
	private String preview;
	private Integer viewNumber;
	private Integer buyNumber;
	private Integer bestNy;
	private Integer todayPickNy;
	private Integer mdPickNy;
	private Integer freeShippingNy;
	private Integer taxDeductionNy;
	private String sliderText;
	private Date regDate;
	private Date modDate;
	private Integer delNy;
	
	private String name;
	private String authorInfo;
	private String prodTypeName;
	private String publisherName;
	private String authorTypeName;
	
	private Integer authorCount;
//	private String author_seq;
	private String otherAuthors;
	private Integer type;
	private String pseq;
//---------------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getProdType() {
		return prodType;
	}
	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}
	public Integer getPublisher() {
		return publisher;
	}
	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}
	public Date getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSale() {
		return sale;
	}
	public void setSale(Integer sale) {
		this.sale = sale;
	}
	public Integer getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getStockQty() {
		return stockQty;
	}
	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Double getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(Double reviewNum) {
		this.reviewNum = reviewNum;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public String getTableOfContents() {
		return tableOfContents;
	}
	public void setTableOfContents(String tableOfContents) {
		this.tableOfContents = tableOfContents;
	}
	public String getPublisherReview() {
		return publisherReview;
	}
	public void setPublisherReview(String publisherReview) {
		this.publisherReview = publisherReview;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public Integer getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}
	public Integer getBuyNumber() {
		return buyNumber;
	}
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
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
	public String getSliderText() {
		return sliderText;
	}
	public void setSliderText(String sliderText) {
		this.sliderText = sliderText;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthorInfo() {
		return authorInfo;
	}
	public void setAuthorInfo(String authorInfo) {
		this.authorInfo = authorInfo;
	}
	public String getProdTypeName() {
		return prodTypeName;
	}
	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getAuthorTypeName() {
		return authorTypeName;
	}
	public void setAuthorTypeName(String authorTypeName) {
		this.authorTypeName = authorTypeName;
	}
	//	public String getAuthor_seq() {
//		return author_seq;
//	}
//	public void setAuthor_seq(String author_seq) {
//		this.author_seq = author_seq;
//	}
	public Integer getAuthorCount() {
		return authorCount;
	}
	public void setAuthorCount(Integer authorCount) {
		this.authorCount = authorCount;
	}
	public String getOtherAuthors() {
		return otherAuthors;
	}
	public void setOtherAuthors(String otherAuthors) {
		this.otherAuthors = otherAuthors;
	}
	public String getPseq() {
		return pseq;
	}
	public void setPseq(String pseq) {
		this.pseq = pseq;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
