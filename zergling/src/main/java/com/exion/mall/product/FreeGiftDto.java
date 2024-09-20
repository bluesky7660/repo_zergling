package com.exion.mall.product;

import java.util.Date;

public class FreeGiftDto {
	private String fgSeq;	
	private String fgName;
	private Integer fgPrice;
	private Integer fgStockQty;
	private String freeGiftInfo;
	private Date fgRegDate;
	private Date fgModDate;
	private Integer fgDelNy;
	private Integer fgUseNy;
//-----------------------------
	public String getFgSeq() {
		return fgSeq;
	}
	public void setFgSeq(String fgSeq) {
		this.fgSeq = fgSeq;
	}
	public String getFgName() {
		return fgName;
	}
	public void setFgName(String fgName) {
		this.fgName = fgName;
	}
	public Integer getFgPrice() {
		return fgPrice;
	}
	public void setFgPrice(Integer fgPrice) {
		this.fgPrice = fgPrice;
	}
	public Integer getFgStockQty() {
		return fgStockQty;
	}
	public void setFgStockQty(Integer fgStockQty) {
		this.fgStockQty = fgStockQty;
	}
	public String getFreeGiftInfo() {
		return freeGiftInfo;
	}
	public void setFreeGiftInfo(String freeGiftInfo) {
		this.freeGiftInfo = freeGiftInfo;
	}
	public Date getFgRegDate() {
		return fgRegDate;
	}
	public void setFgRegDate(Date fgRegDate) {
		this.fgRegDate = fgRegDate;
	}
	public Date getFgModDate() {
		return fgModDate;
	}
	public void setFgModDate(Date fgModDate) {
		this.fgModDate = fgModDate;
	}
	public Integer getFgDelNy() {
		return fgDelNy;
	}
	public void setFgDelNy(Integer fgDelNy) {
		this.fgDelNy = fgDelNy;
	}
	public Integer getFgUseNy() {
		return fgUseNy;
	}
	public void setFgUseNy(Integer fgUseNy) {
		this.fgUseNy = fgUseNy;
	}
	
}
