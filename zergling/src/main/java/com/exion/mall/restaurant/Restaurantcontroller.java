package com.exion.mall.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Restaurantcontroller {
	@Autowired
	RestaurantService restaurantService;
	
	@RequestMapping(value = "/v1/mall/restaurant/restaurantXdm")
	public String restaurantXdm(Model model) {
		List<RestaurantDto> rests =  restaurantService.selectRest();
		for(RestaurantDto rest:rests) {
			System.out.println("날짜: "+ rest.getBusinessHoursEnd());
//			System.out.printf("|%-5s|%-5s|\n",codeGroup.getSeq(),codeGroup.getCodeGroupName());
		}
		model.addAttribute("list", restaurantService.selectRest()) ;
		model.addAttribute("totalRows", restaurantService.selectRest().size()) ;
		return "/xdm/v1/mall/restaurant/restaurantXdm";
	}
}
