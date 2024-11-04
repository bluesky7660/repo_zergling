package com.exion.mall.product;

import com.exion.infra.codegroup.BaseVo;

public class OrderVo extends BaseVo{
	private Integer orderStatus;
	private Integer minPrice;
	private Integer maxPrice;
//---------------------------------

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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
	
}
