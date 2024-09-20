package com.exion.mall.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreeGiftController {
	
	@Autowired
	FreeGiftService freeGiftService;
	
	@RequestMapping(value = "v1/mall/product/freeGiftXdmList")
	public String freeGiftXdmList(Model model){
		model.addAttribute("list", freeGiftService.fgList());
		return "xdm/v1/mall/product/freeGiftXdm";
	}
	
	@RequestMapping(value = "v1/mall/product/freeGiftXdmForm")
	public String freeGiftXdmForm(){
		return "xdm/v1/mall/product/freeGiftXdmForm";
	}
	
	@RequestMapping(value = "v1/mall/product/freeGiftXdmMfom")
	public String freeGiftXdmMfom(Model model,FreeGiftDto freeGiftDto){
		model.addAttribute("item", freeGiftService.fgOne(freeGiftDto));
		return "xdm/v1/mall/product/freeGiftXdmMfom";
	}
	
	@RequestMapping(value = "v1/mall/product/freeGiftXdmInst")
	public String freeGiftXdmInst(FreeGiftDto freeGiftDto){
		freeGiftService.insert(freeGiftDto);
		return "redirect:freeGiftXdmList";
	}

}
