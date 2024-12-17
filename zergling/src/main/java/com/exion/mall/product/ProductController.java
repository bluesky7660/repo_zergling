package com.exion.mall.product;

import java.util.Date;
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

import com.exion.common.util.DateUtil;
import com.exion.infra.code.CodeDto;
import com.exion.infra.code.CodeService;
import com.exion.infra.codegroup.BaseVo;

import jakarta.servlet.http.HttpSession;

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
	@Autowired
	ReviewService reviewService;
	@Autowired
	DeliveryAddressService deliveryAddressService;
	
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
	
	/*usr*/
	
	@RequestMapping(value = "product_detail")
	public String productDetail(Model model,@ModelAttribute("vo") ReviewVo reviewVo, ReviewDto reviewDto, ProductDto productDto,ProductVo vo,AuthorVo authorVo,ProductAuthorDto productAuthorDto) {
		System.out.println("productDetail");
		reviewVo.setParamsPaging(reviewService.listCount(reviewVo));
		List<ReviewDto> lists = reviewService.selectUsrList(reviewVo);
		for(ReviewDto list:lists) {
			System.out.println("점수: "+list.getRvScore()+" , 이름: "+list.getRvName());
		}
		for(CodeDto code:codeService.tagsList()) {
			System.out.println("rvTags: " +code.getCodeName());
		}
		System.out.println("SEQ: "+productService.prodOne(productDto).getSeq()+" , 제목: "+productService.prodOne(productDto).getTitle());
		model.addAttribute("product", productService.prodUsrOne(vo));
//		vo.setProdType(null);
		System.out.println("ProdType:" +productService.prodOne(productDto).getProdType());
		vo.setProdType(productService.prodOne(productDto).getProdType());
		vo.setSeq(productService.prodOne(productDto).getSeq());
		System.out.println("카테고리:" +productService.prodOne(productDto).getProdTypeName());
		Date deliveryDate = DateUtil.getDeliveryDate(2);
		model.addAttribute("deliveryDate",deliveryDate);
		model.addAttribute("best", productService.bestCategoryProdList(vo));
		model.addAttribute("prodAuthor", authorService.prodAuthorList(authorVo));
		model.addAttribute("authors", authorService.authorUsrList(authorVo));
		model.addAttribute("rvTags", codeService.tagsList());
		model.addAttribute("rvCount", reviewService.listCount(reviewVo));
		model.addAttribute("rvList", reviewService.selectUsrList(reviewVo));
		List<ProductDto> imgList = productService.imgUsrList(productDto);
        
        // 리스트의 내용을 출력합니다.
        for (ProductDto img : imgList) {
            System.out.println("Pseq: " + img.getPseq()); // Pseq 출력
            System.out.println("type: " + img.getType()); // Pseq 출력
            System.out.println("ImgSrc: " + img.getImgSrc()); // 이미지 경로 출력
            // 필요한 다른 속성도 추가적으로 출력할 수 있습니다.
        }
		model.addAttribute("prodImg", productService.imgUsrList(productDto));
		return "usr/v1/pages/product_detail";
	}
	
	@RequestMapping(value = "product_list")
	public String productList(Model model,ProductDto productDto,@ModelAttribute("vo") ProductVo productVo,@ModelAttribute("shVo") BaseVo shVo,ReviewVo reviewVo) {
		productVo.setParamsPaging(productService.listCount(productVo));
		System.out.println("get: "+productVo.getMakeDateFillter());
		System.out.println("검색어: "+productVo.getSearchKeyword());
//		
		model.addAttribute("list", productService.usrProdList(productVo));
		model.addAttribute("bages", codeService.bageList());
		model.addAttribute("reviewStats", reviewService.listScore(reviewVo, productVo));
		List<ProductDto> imgList = productService.imgUsrList(productDto);
        
        // 리스트의 내용을 출력합니다.
        for (ProductDto img : imgList) {
            System.out.println("Pseq: " + img.getPseq()); // Pseq 출력
            System.out.println("ImgSrc: " + img.getImgSrc()); // 이미지 경로 출력
            // 필요한 다른 속성도 추가적으로 출력할 수 있습니다.
        }
        Date deliveryDate = DateUtil.getDeliveryDate(2);
		model.addAttribute("deliveryDate",deliveryDate);
		return "usr/v1/pages/product_list";
	}
	
	@RequestMapping(value = "product_buy")
	public String productBuy(Model model, @RequestParam(value = "isSelected", required = false) Boolean isSelected, @RequestParam(value="daSeq", required = false) String daSeq, DeliveryAddressDto deliveryAddressDto,ProductVo productVo, DeliveryAddressVo addressVo, HttpSession session) {
		
		String mmSeq = (String) session.getAttribute("sessSeqXdm");
		deliveryAddressDto.setSeq(mmSeq);
		addressVo.setSeq(mmSeq);
		System.out.println("isSelected: " +isSelected +" , daSeq: "+daSeq);
		
		if(Boolean.TRUE.equals(isSelected)) {
			System.out.println("셀렉트");
			model.addAttribute("user", deliveryAddressService.selectOne(deliveryAddressDto));
		}else {
			System.out.println("usr.daseq: " +(deliveryAddressService.selectDefOne(deliveryAddressDto) != null ? deliveryAddressService.selectDefOne(deliveryAddressDto).getDaSeq(): "null"));
			model.addAttribute("user", deliveryAddressService.selectDefOne(deliveryAddressDto));
		}
//		model.addAttribute("user", deliveryAddressService.selectDefOne(deliveryAddressDto));
		model.addAttribute("item", productService.prodUsrOne(productVo));
		Date deliveryDate = DateUtil.getDeliveryDate(2);
		model.addAttribute("deliveryDate",deliveryDate);
		model.addAttribute("addrList", deliveryAddressService.selectUsrList(addressVo));
		System.out.println("product_buy");
		return "usr/v1/pages/product_buy";
	}
	
	@ResponseBody
	@RequestMapping(value = "buyAddressChange")
	public Map<String, Object> buyAddressChange(Model model,@RequestParam("daSeq") String daSeq,@RequestParam("seq") String seq, DeliveryAddressDto deliveryAddressDto) {
		deliveryAddressDto.setDaSeq(daSeq);
		Map<String, Object> returnMap = new HashMap<>();
		DeliveryAddressDto addr = deliveryAddressService.selectOne(deliveryAddressDto);
		if(addr != null) {
//			model.addAttribute("user", deliveryAddressService.selectOne(deliveryAddressDto));
			System.out.println("성공");
			returnMap.put("rt", "success");
			returnMap.put("data", addr);
		}
		else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		System.out.println("주소이름: "+addr.getAddressName());
		System.out.println("도로명: "+addr.getDaRoadAddress());
		System.out.println("참조: "+addr.getDaExtraAddress());
		System.out.println("우편번호: "+addr.getDaZonecode());
		System.out.println("우편번호: "+addr.getRecipientPhone());
		System.out.println("우편번호: "+addr.getRecipientName());
//		System.out.println("우편번호: "+addr.getDaZonecode());
//		System.out.println("상품seq: "+seq);
		
		return returnMap;
	}
}
