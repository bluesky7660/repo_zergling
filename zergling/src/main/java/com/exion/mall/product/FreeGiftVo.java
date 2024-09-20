package com.exion.mall.product;

import com.exion.infra.codegroup.BaseVo;

public class FreeGiftVo extends BaseVo{
	private String fgSeq;
	private String fgName;
	private Integer fgPrice;
	private Integer fgStockQty;
	
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
	
}
