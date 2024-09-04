package com.exion.mall.product;

import java.util.List;

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
	
	@Autowired
	ProductService productService;
	
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
		return "redirect:login";
//		return "redirect:/v1/infra/user/userXdmList";
	}
	@RequestMapping(value = "user_delivery_address")
	public String deliveryAddress(Model model,DeliveryAddressDto deliveryAddressDto) {
		model.addAttribute("item", deliveryAddressService.selectOne(deliveryAddressDto));
		model.addAttribute("addrList", deliveryAddressService.selectList());
		return "/usr/v1/pages/user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_addressMfom")
	public String deliveryAddressMfom(Model model,DeliveryAddressDto deliveryAddressDto) {
		model.addAttribute("item", deliveryAddressService.selectOne(deliveryAddressDto));
		return "/usr/v1/pages/user_delivery_addressMfom";
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
	public String userAccount(Model model, UserDto userDto) {
		model.addAttribute("item", userService.selectOne(userDto));
		System.out.println("seq: "+userService.selectOne(userDto).getSeq());
		return "/usr/v1/pages/user_account";
	}
	@RequestMapping(value = "user_accountUpdt")
	public String user_accountUpdt(UserDto userDto) {
		System.out.println("seq1: "+userDto.getSeq());
		userService.update(userDto);
		return "redirect:index";
	}
	@RequestMapping(value = "user_order_list")
	public String userOrderList() {
		return "/usr/v1/pages/user_order_list";
	}
	@RequestMapping(value = "user_password")
	public String userPassword(Model model,UserDto userDto) {
		model.addAttribute("item", userService.selectOne(userDto));
		return "/usr/v1/pages/user_password";
	}
	
	@RequestMapping(value = "product_detail")
	public String productDetail(Model model,ProductDto productDto) {
//		List<ProductDto> prodLists = productService.prodList();
//		for(ProductDto prodList:prodLists) {
//			System.out.println("SEQ: "+prodList.getSeq()+" , 제목: "+prodList.getTitle());
//		}
		System.out.println("SEQ: "+productService.prodOne(productDto).getSeq()+" , 제목: "+productService.prodOne(productDto).getTitle());
		model.addAttribute("product", productService.prodOne(productDto));
		return "usr/v1/pages/product_detail";
	}
	@RequestMapping(value = "product_list")
	public String productList() {
		
		return "/usr/v1/pages/product_list";
	}
	
	@RequestMapping(value = "product_buy")
	public String productBuy() {
		
		return "/usr/v1/pages/product_buy";
	}
	@RequestMapping(value = "account_recovery")
	public String accountRecovery() {
		
		return "/usr/v1/pages/account_recovery";
	}
}
