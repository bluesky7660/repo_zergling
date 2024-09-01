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
	
	@RequestMapping(value = "/v1/mall/restaurant/restaurantXdmList")
	public String restaurantXdm(Model model) {
		model.addAttribute("list", restaurantService.selectRest()) ;
		model.addAttribute("totalRows", restaurantService.selectRest().size()) ;
		return "xdm/v1/mall/restaurant/restaurantXdm";
	}
	@RequestMapping(value = "/v1/mall/restaurant/restaurantXdmForm")
	public String restaurantXdmForm() {
		
		return"xdm/v1/mall/restaurant/restaurantXdmForm";
	}
	@RequestMapping(value = "/v1/mall/restaurant/restaurantXdmInst")
	public String restaurantXdmInst(RestaurantDto restaurantDto) {
		restaurantService.insert(restaurantDto);
		return"redirect:/v1/mall/restaurant/restaurantXdmList";
	}
}
