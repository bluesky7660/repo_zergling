package com.exion.mall.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.user.UserDto;
import com.exion.infra.user.UserService;

@Controller
public class indexController {
	@Autowired
	DeliveryAddressService deliveryAddressService;
	
	@Autowired
	UserService userService;
	
//	@Autowired
//	UserDto 
	
	@RequestMapping(value = "index")
	public String index() {
		return "/usr/v1/pages/index";
	}
	@RequestMapping(value = "login")
	public String login() {
		return "/usr/v1/pages/login";
	}
	@RequestMapping(value = "signup")
	public String signup() {
		return "/usr/v1/pages/signup";
	}
	@RequestMapping(value = "signupInst")
	public String signupInst(UserDto userDto) {
		userService.insertUser(userDto);
		return "redirect:/v1/infra/user/userXdmList";
	}
	@RequestMapping(value = "user_delivery_address")
	public String deliveryAddress(Model model) {
		model.addAttribute("addrList", deliveryAddressService.selectList());
		return "/usr/v1/pages/user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_address_add")
	public String deliveryAddressAddForm() {
		
		return "/usr/v1/pages/user_delivery_address_add";
	}
	@RequestMapping(value = "user_delivery_address_inst")
	public String deliveryAddressAddForm(DeliveryAddressDto deliveryAddressDto) {
		deliveryAddressService.insertAddr(deliveryAddressDto);
		return "redirect:user_delivery_address";
	}
	@RequestMapping(value = "user_account")
	public String userAccount() {
		return "/usr/v1/pages/user_account";
	}
	
	@RequestMapping(value = "product_detail")
	public String productDetail() {
		
		return "/usr/v1/pages/product_detail";
	}
	@RequestMapping(value = "product_list")
	public String productList() {
		
		return "/usr/v1/pages/product_list";
	}
}
