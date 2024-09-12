package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(value = "v1/mall/product/productXdmList")
	public String productXdmList(Model model){
		model.addAttribute("list", productService.xdmProdList());
		return "xdm/v1/mall/product/productXdm";
	}
	@RequestMapping(value = "v1/mall/product/productXdmForm")
	public String productXdmForm() {
		return "xdm/v1/mall/product/productXdmForm";
	}
	@RequestMapping(value = "v1/mall/product/productXdmInst")
	public String productXdmInst(ProductDto productDto) {
		productService.insertProd(productDto);
		return "redirect:productXdmList";
	}
	
	@RequestMapping(value = "v1/mall/product/productXdmMfom")
	public String productXdmMfom(Model model, ProductDto productDto , AuthorDto authorDto) {
		model.addAttribute("item", productService.prodOne(productDto));
		model.addAttribute("author", authorService.authorOne(authorDto));
		return "xdm/v1/mall/product/productXdmMfom";
	}
	
	@RequestMapping(value = "v1/mall/product/productXdmUpdt")
	public String productXdmUpdt(ProductDto productDto) {
		productService.update(productDto);
		return "redirect:productXdmList";
	}
}
