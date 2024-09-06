package com.exion.mall.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(value = "v1/mall/product/authorXdmList")
	public String authorXdmList(Model model){
		model.addAttribute("list", authorService.authorList());
		return "xdm/v1/mall/product/authorXdm";
	}
//	@RequestMapping(value = "v1/mall/product/productXdmForm")
//	public String productXdmForm() {
//		return "xdm/v1/mall/product/productXdmForm";
//	}
//	@RequestMapping(value = "v1/mall/product/productXdmInst")
//	public String productXdmInst(ProductDto productDto) {
//		productService.insertProd(productDto);
//		return "redirect:productXdmList";
//	}
//	
//	@RequestMapping(value = "v1/mall/product/productXdmMfom")
//	public String productXdmMfom() {
//		return "xdm/v1/mall/product/productXdmMfom";
//	}
}
