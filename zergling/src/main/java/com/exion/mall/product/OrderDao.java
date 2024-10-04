package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	public List<OrderDto> selectUsrList(OrderDto orderDto);
}
