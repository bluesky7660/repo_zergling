package com.exion.infra.kakao;

import com.exion.mall.product.OrderDto;

public class OrderCreateForm extends OrderDto{
	private String name;      // 주문 상품 이름
	private int quantity;      // 주문 수량
    private int totalPrice;   // 주문 금액
    private String userId;
    
// Getter와 Setter 메서드
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
   
}
