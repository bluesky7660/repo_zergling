package com.exion.mall.product;

import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.code.CodeDto;
import com.exion.infra.code.CodeService;

@Controller
public class AuthorController {
	@Autowired
	AuthorService authorService;
	
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "v1/mall/product/authorXdmList")
	public String authorXdmList(Model model){
		model.addAttribute("list", authorService.authorList());
		return "xdm/v1/mall/product/authorXdm";
	}
	@RequestMapping(value = "v1/mall/product/authorXdmForm")
	public String authorXdmForm(CodeDto codeDto, Model model) {
//		List<CodeDto> codes = codeService.selectList();
//		List<CodeDto> jobs = codes.stream().filter(code -> code.getCodeGroup_seq() == "10").collect(Collectors.toList());
//		model.addAttribute("jobs", jobs);
		return "xdm/v1/mall/product/authorXdmForm";
	}
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
