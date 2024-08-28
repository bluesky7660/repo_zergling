package com.exion.mall.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Restaurantcontroller {
	@Autowired
	RestaurantService restaurantService;
	
	@RequestMapping(value = "/v1/mall/restaurant/restaurantXdm")
	public String restaurantXdm() {
		List<RestaurantDto> rests = restaurantService.selectRest();
		for(RestaurantDto rest:rests) {
			System.out.printf("|%-5s|%-5s|%-5s|\n",rest.getSeq(),rest.getFoodType(),rest.getName());
		}
		return "/xdm/v1/mall/restaurant/restaurantXdm";
	}
}
