package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.code.CodeService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	AuthorService authorService;
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "v1/mall/product/productXdmList")
	public String productXdmList(Model model,ProductVo vo){
		model.addAttribute("list", productService.xdmProdList(vo));
		model.addAttribute("groups", codeService.prodTypeList());
		return "xdm/v1/mall/product/productXdm";
	}
	@RequestMapping(value = "v1/mall/product/productXdmForm")
	public String productXdmForm(Model model, AuthorVo vo) {
		model.addAttribute("publishers", codeService.publisherList());
		model.addAttribute("prodTypes", codeService.prodTypeList());
		model.addAttribute("author", authorService.authorList(vo));
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
		model.addAttribute("prodTypes", codeService.prodTypeList());
		return "xdm/v1/mall/product/productXdmMfom";
	}
	
	@RequestMapping(value = "v1/mall/product/productXdmUpdt")
	public String productXdmUpdt(ProductDto productDto) {
		productService.update(productDto);
		return "redirect:productXdmList";
	}
}
