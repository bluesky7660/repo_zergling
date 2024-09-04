package com.exion.mall.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
	@Autowired
	RestaurantDao restaurantDao;
	
	public List<RestaurantDto> selectRest(){
		List<RestaurantDto> rests = restaurantDao.selectRest();
		return rests;
	}
	public int insert(RestaurantDto restaurantDto) {
		return restaurantDao.insert(restaurantDto);
	}
	public RestaurantDto selectOne(RestaurantDto restaurantDto) {
		return restaurantDao.selectOne(restaurantDto);
	}
	public int update(RestaurantDto restaurantDto) {
		return restaurantDao.update(restaurantDto);
	}
}
