package com.exion.mall.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	@RequestMapping("/v1/mall/order/orderXdmList")
	public String orderXdmList(Model model,@ModelAttribute("vo") OrderVo vo) {
		vo.setParamsPaging(orderService.listCount(vo));
		model.addAttribute("list", orderService.selectList(vo));
		return "xdm/v1/mall/order/orderXdmList";
	}
}
