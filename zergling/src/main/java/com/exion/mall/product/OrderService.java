package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	OrderDao orderDao;
	
	public List<OrderDto> selectList(OrderVo vo){
		return orderDao.selectList(vo);
	}
	public List<OrderDto> selectUsrList(OrderDto orderDto){
		return orderDao.selectUsrList(orderDto);
	}
	public OrderDto selectUsrOne(OrderDto orderDto){
		return orderDao.selectUsrOne(orderDto);
	}
	public int listCount(OrderVo vo) {
		return orderDao.listCount(vo);
	}
	public int cancel(OrderDto orderDto) {
		return orderDao.cancel(orderDto);
	}
	public int insert(OrderDto orderDto) {
		return orderDao.insert(orderDto);
	}
}
