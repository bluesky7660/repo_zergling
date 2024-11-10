package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exion.infra.code.CodeService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	AuthorService authorService;
	@Autowired
	CodeService codeService;
	@Autowired
	ProductAuthorService productAuthorService ;
	
	@RequestMapping(value = "v1/mall/product/productXdmList")
	public String productXdmList(Model model,@ModelAttribute("vo") ProductVo vo){
		vo.setParamsPaging(productService.listCount(vo));
		System.out.println("---------------------------------------------");
		System.out.println("번호thisPage: " + vo.getThisPage());
		System.out.println("번호StartPage: " + vo.getStartPage());
		System.out.println("번호EndPage: " + vo.getEndPage());
		System.out.println("번호TotalPages: " + vo.getTotalPages());
		System.out.println("번호TotalRows: " + vo.getTotalRows());
		System.out.println("번호PageNumToShow: " + vo.getPageNumToShow());
		System.out.println("번호RowNumToShow: " + vo.getRowNumToShow());
		System.out.println("번호StartRnumForMysql: " + vo.getStartRnumForMysql());
		System.out.println("---------------------------------------------");
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
	public String productXdmInst(ProductDto productDto,ProductAuthorDto productAuthorDto) throws Exception {
		productService.insertProd(productDto,productAuthorDto,"productUpload");
		
		return "redirect:productXdmList";
	}
	
	@RequestMapping(value = "v1/mall/product/productXdmMfom")
	public String productXdmMfom(Model model, ProductDto productDto , AuthorDto authorDto, AuthorVo vo,ProductAuthorDto productAuthorDto) {
//		System.out.println("리스트1: "+productService.prodOne(productDto).getAuthor_seq());
		model.addAttribute("item", productService.prodOne(productDto));
		System.out.println("리스트: "+productAuthorService.productAuthorSelected(productAuthorDto).get(0).getAuthor_seq());
		model.addAttribute("publishers", codeService.publisherList());
//		model.addAttribute("author", authorService.authorOne(authorDto));
		model.addAttribute("authors", productAuthorService.productAuthorSelected(productAuthorDto));
		model.addAttribute("authorList", authorService.authorList(vo));
		model.addAttribute("imgList", productService.imgList(productDto));
		model.addAttribute("prodTypes", codeService.prodTypeList());
		return "xdm/v1/mall/product/productXdmMfom";
	}
	
	@RequestMapping(value = "v1/mall/product/productXdmUpdt")
	public String productXdmUpdt(@RequestParam("author_seq") List<String> listAuthor_seq, ProductDto productDto,ProductAuthorDto productAuthorDto) {
		System.out.println("Author Sequences: " + listAuthor_seq);
		productAuthorDto.setListAuthor_seq(listAuthor_seq);
		try {
			productService.update(productDto,productAuthorDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:productXdmList";
	}
}
