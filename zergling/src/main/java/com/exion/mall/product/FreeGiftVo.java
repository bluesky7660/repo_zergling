package com.exion.mall.product;

import com.exion.infra.codegroup.BaseVo;

public class FreeGiftVo extends BaseVo{
	private String fgSeq;
	private String fgName;
	private Integer fgPrice;
	private Integer fgStockQty;
	private Integer fgStockQtyNy;
	private Integer minPrice;
	private Integer maxPrice;
//	
//	private Integer fgDelNy;
//	private Integer fgUseNy;
//
//------------------------------

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

	public Integer getFgStockQtyNy() {
		return fgStockQtyNy;
	}

	public void setFgStockQtyNy(Integer fgStockQtyNy) {
		this.fgStockQtyNy = fgStockQtyNy;
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

//	public Integer getFgDelNy() {
//		return fgDelNy;
//	}
//
//	public void setFgDelNy(Integer fgDelNy) {
//		this.fgDelNy = fgDelNy;
//	}
//
//	public Integer getFgUseNy() {
//		return fgUseNy;
//	}
//
//	public void setFgUseNy(Integer fgUseNy) {
//		this.fgUseNy = fgUseNy;
//	}
	
}
