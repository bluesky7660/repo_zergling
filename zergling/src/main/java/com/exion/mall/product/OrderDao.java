package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
	public List<OrderDto> selectList(OrderVo vo);
	public List<OrderDto> selectUsrList(OrderDto orderDto);
	public int listCount(OrderVo vo);
	public int insert(OrderDto orderDto);
}
