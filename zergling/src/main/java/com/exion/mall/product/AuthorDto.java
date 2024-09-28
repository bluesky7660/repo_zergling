package com.exion.mall.product;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthorDto {
	private String seq;
	private int authorType;
	private String codeName;
	
	private String name;
	private String authorInfo;
	private String title;
	private Date regDate;
	private Date modDate;
	private Integer delNy;
	
	private Integer productCount;
	private String imgSrc;
	private Map<String, String> titleImageMap = new HashMap<>();
	
	public void addTitleImage(String title, String imgSrc) {
        titleImageMap.put(title, imgSrc);
    }
//	public void addTitle(String title) {
//        if (this.titleList == null) {
//            this.titleList = new ArrayList<>();
//        }
//        this.titleList.add(title);
//    }
//	public void addImgSrc(String imgSrc) {
//        if (this.imgSrcList == null) {
//            this.imgSrcList = new ArrayList<>();
//        }
//        this.imgSrcList.add(imgSrc);
//    }
//-------------------------------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public int getAuthorType() {
		return authorType;
	}
	public void setAuthorType(int authorType) {
		this.authorType = authorType;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public Map<String, String> getTitleImageMap() {
		return titleImageMap;
	}
	public void setTitleImageMap(Map<String, String> titleImageMap) {
		this.titleImageMap = titleImageMap;
	}
	
	
}
