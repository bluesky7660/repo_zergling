package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	public List<OrderDto> selectUsrList(OrderDto orderDto){
		return orderDao.selectUsrList(orderDto);
	}
	public int insert(OrderDto orderDto) {
		return orderDao.insert(orderDto);
	}
}
