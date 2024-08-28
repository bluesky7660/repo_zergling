package com.exion.mall.restaurant;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantDao {
	public List<RestaurantDto> selectRest();
}
