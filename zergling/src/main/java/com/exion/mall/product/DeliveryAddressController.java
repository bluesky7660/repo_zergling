package com.exion.mall.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DeliveryAddressController {
	@Autowired
	DeliveryAddressService deliveryAddressService;
	
	@RequestMapping("/v1/mall/address/deliveryAddressXdmList")
	public String deliveryAddressXdmList(Model model,@ModelAttribute("vo") DeliveryAddressVo vo) {
		vo.setParamsPaging(deliveryAddressService.listCount(vo));
		model.addAttribute("list", deliveryAddressService.selectList(vo));
		return "xdm/v1/mall/address/deliveryAddressXdmList";
	} 
}
