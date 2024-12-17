package com.exion.infra.dash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.member.MemberService;
import com.exion.infra.member.MemberVo;
import com.exion.mall.product.OrderService;
import com.exion.mall.product.OrderVo;
import com.exion.mall.product.ProductService;
import com.exion.mall.product.ProductVo;

@Controller
public class DashboardController {
	@Autowired
	OrderService orderService;
	@Autowired
	MemberService memberService;
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "v1/infra/index/indexXdmView")
	public String indexXdmView(Model model,OrderVo orderVo,MemberVo memberVo,ProductVo productVo) {
		model.addAttribute("orderList", orderService.selectList(orderVo));
		model.addAttribute("orderCount", orderService.listCount(orderVo));
		orderVo.setOrderStatus(56);
		model.addAttribute("orderPaied", orderService.listCount(orderVo));
		orderVo.setOrderStatus(57);
		model.addAttribute("orderReady", orderService.listCount(orderVo));
		orderVo.setOrderStatus(14);
		model.addAttribute("orderdTransit", orderService.listCount(orderVo));
		orderVo.setOrderStatus(10);
		model.addAttribute("orderDelivered", orderService.listCount(orderVo));
		orderVo.setOrderStatus(19);
		model.addAttribute("orderCanceled", orderService.listCount(orderVo));
//		orderVo.setOrderStatus(null);
		model.addAttribute("orderMonthCount", orderService.orderMonthCount());
		model.addAttribute("memberCount", memberService.listCount(memberVo));
		model.addAttribute("productCount", productService.listCount(productVo));
		model.addAttribute("revenue",orderService.revenue());
		
		
		return "xdm/v1/infra/index/index";
	}
}
