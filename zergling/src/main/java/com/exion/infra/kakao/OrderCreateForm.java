package com.exion.infra.kakao;

public class OrderCreateForm {
	private String name;      // 주문 상품 이름
    private int totalPrice;   // 주문 금액

    // Getter와 Setter 메서드
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
