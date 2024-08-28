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
}
