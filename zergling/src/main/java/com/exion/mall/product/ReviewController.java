package com.exion.mall.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value = "/v1/mall/product/reviewXdmList")
	public String reviewXdmList(Model model,@ModelAttribute("vo") ReviewVo reviewVo) {
		reviewVo.setParamsPaging(reviewService.listCount(reviewVo));
		model.addAttribute("list", reviewService.selectUsrList(reviewVo));
		return "xdm/v1/mall/product/reviewXdmList";
	}
	@RequestMapping(value = "/v1/mall/product/reviewXdmMfom")
	public String reviewXdmMfom(Model model) {
		
		return "xdm/v1/mall/product/reviewXdmMfom";
	}
	@RequestMapping(value = "/v1/mall/product/reviewXdmUelt")
	public String reviewXdmUelt(ReviewDto reviewDto) {
		reviewService.uelete(reviewDto);
		return "redirect:/v1/mall/product/reviewXdmList";
	}
	
}
