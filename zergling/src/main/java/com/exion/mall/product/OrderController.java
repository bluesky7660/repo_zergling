package com.exion.mall.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.infra.member.MemberDto;

import jakarta.servlet.http.HttpSession;

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
	
	/*usr*/
	@RequestMapping(value = "user_order_list")
	public String userOrderList(Model model, HttpSession httpSession, OrderDto orderDto, MemberDto memberDto,ProductVo productVo) {
//		model.addAttribute("member", memberService.selectOne(memberDto));
		orderDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		List<OrderDto> orderlist = orderService.selectUsrList(orderDto);
		model.addAttribute("orderlist", orderlist);
		int readyCount = 0;
		int inDeliveryCount = 0;
		int deliveredCount = 0;
		int cancelledCount = 0;
		int paymentDoneCount = 0;
		for(OrderDto order : orderlist) {
			switch (order.getOrderStatus()) {
				case 57: {
					readyCount++;
					break;
				}
				case 14: {
					inDeliveryCount++;
					break;
				}
				case 10: {
					deliveredCount++;
					break;
				}
				case 19: {
					cancelledCount++;
					break;
				}
				case 56: {
					paymentDoneCount++;
					break;
				}
			}
//			default:
//				
//			}
		}
		// 모델에 각 카운트 값 추가
		model.addAttribute("readyCount", readyCount);
		model.addAttribute("inDeliveryCount", inDeliveryCount);
		model.addAttribute("deliveredCount", deliveredCount);
		model.addAttribute("cancelledCount", cancelledCount);
		model.addAttribute("paymentDoneCount", paymentDoneCount);
//		System.out.println("orderDto:"+orderDto.getProduct_seq());
		System.out.println("orderDto:"+orderDto.getSeq());
//		System.out.println("selectUsrList:"+orderService.selectUsrList(orderDto).get(0).getDeliveryDate()+", "+orderService.selectUsrList(orderDto).get(0).getOrderNumber());
//		productVo.setSeq(orderService.selectUsrList(orderDto).get(0).getProduct_seq());
//		model.addAttribute("prod", productService.prodUsrOne(productVo));
		model.addAttribute("userPage", "orderList");
		System.out.println("user_order_list");
		return "usr/v1/pages/user_order_list";
	}
//	@RequestMapping(value = "orderinst")
//	public String orderinst(Model model, HttpSession httpSession, OrderDto orderDto, MemberDto memberDto) {
//		
//		return "redirect:user_order_list";
//	}
	
	@ResponseBody
	@RequestMapping(value = "orderinst")
	public Map<String, Object> orderinst(OrderDto orderDto){
//		orderDto.setProduct_seq(orderDto.getSeq()); 
		System.out.println("orderinst");
		System.out.println("걍seq:"+orderDto.getSeq());
		System.out.println("상품seq:"+orderDto.getProduct_seq());
		System.out.println("유저seq:"+orderDto.getMmSeq());
		System.out.println("주소seq:"+orderDto.getDaSeq());
		Integer rtOrder = orderService.insert(orderDto);
		
		Map<String, Object> returnMap = new HashMap<>();
		if (rtOrder != null) {
			System.out.println("성공");
			returnMap.put("rt", "success");
		} else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	@ResponseBody
	@RequestMapping(value = "orderCancel")
	public Map<String, Object> orderCancel(@RequestParam("uoSeq") String uoSeq , OrderDto orderDto){
		Map<String, Object> returnMap = new HashMap<>();
		System.out.println("uoSeq:"+uoSeq);
		orderDto.setUoSeq(uoSeq);
		orderService.cancel(orderDto);
	    OrderDto rtOrder = orderService.selectUsrOne(orderDto);
	    System.out.println("rtOrder.getOrderDate():"+rtOrder.getOrderDate());
	    if (rtOrder != null) {
	        returnMap.put("rt", "success");
	        returnMap.put("uoSeq", rtOrder.getUoSeq());
	        returnMap.put("orderDate", rtOrder.getOrderDate());
	        returnMap.put("orderNumber", rtOrder.getOrderNumber());
	        returnMap.put("imgSrc", rtOrder.getImgSrc());
	        returnMap.put("prodTypeName", rtOrder.getProdTypeName());
	        returnMap.put("title", rtOrder.getTitle());
	        returnMap.put("uoQuantity", rtOrder.getUoQuantity());
	        returnMap.put("uoPrice", rtOrder.getUoPrice());
	        returnMap.put("orderStatusText", rtOrder.getOrderStatusText());
	        returnMap.put("deliveryDate", rtOrder.getDeliveryDate());
	        returnMap.put("orderStatus", rtOrder.getOrderStatus());
	        System.out.println("성공");
	    } else {
	        returnMap.put("rt", "fail");
	        System.out.println("실패");
	    }
	    
	    return returnMap;
	}
	@ResponseBody
	@RequestMapping(value = "orderUelt")
	public String orderUelt(OrderDto orderDto){
		
		return "returnMap";
	}
}
