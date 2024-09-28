package com.exion.mall.product;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.code.CodeService;
import com.exion.infra.member.MemberDto;
import com.exion.infra.member.MemberService;
import com.exion.infra.user.UserDto;
import com.exion.infra.user.UserService;

@Controller
public class indexController {
	@Autowired
	CodeService codeService;
	
	@Autowired
	DeliveryAddressService deliveryAddressService;
	
//	@Autowired
//	UserService userService;
	@Autowired
	MemberService memberService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	ProductAuthorService productAuthorService;
	
	@RequestMapping(value = "index")
	public String index() {
		return "/usr/v1/pages/index";
	}
	@RequestMapping(value = "login")
	public String login() {
		return "/usr/v1/pages/login";
	}
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute("genders", codeService.genderList());
		return "/usr/v1/pages/signup";
	}
	@RequestMapping(value = "signupInst")
	public String signupInst(MemberDto memberDto) {
		memberService.insertUsr(memberDto);
		return "redirect:login";
	}
//	@RequestMapping(value = "signupInst")
//	public String signupInst(UserDto userDto) {
//		userService.insertUser(userDto);
//		return "redirect:login";
//	}
	@RequestMapping(value = "user_delivery_address")
	public String deliveryAddress(Model model, MemberDto memberDto, DeliveryAddressDto deliveryAddressDto,@ModelAttribute("vo") DeliveryAddressVo vo) {
		model.addAttribute("member", memberService.selectOne(memberDto));
		model.addAttribute("item", deliveryAddressService.selectDefOne(deliveryAddressDto));
		System.out.println("DefSeq: "+deliveryAddressService.selectDefOne(deliveryAddressDto).getSeq());
		model.addAttribute("addrList", deliveryAddressService.selectList(vo));
//		int size = deliveryAddressService.selectList().size();
//		model.addAttribute("size", size); // 사이즈 추가
		return "/usr/v1/pages/user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_addressMfom")
	public String deliveryAddressMfom(Model model,DeliveryAddressDto deliveryAddressDto) {
		model.addAttribute("item", deliveryAddressService.selectOne(deliveryAddressDto));
		System.out.println("MfomSeq: "+deliveryAddressService.selectOne(deliveryAddressDto).getSeq());
		return "/usr/v1/pages/user_delivery_addressMfom";
	}
	@RequestMapping(value = "user_delivery_address_add")
	public String deliveryAddressAddForm() {
		
		return "/usr/v1/pages/user_delivery_address_add";
	}
	@RequestMapping(value = "user_delivery_address_inst")
	public String deliveryAddressInst(DeliveryAddressDto deliveryAddressDto) {
		deliveryAddressService.insertAddr(deliveryAddressDto);
		return "redirect:user_delivery_address?seq=1";
	}
	@RequestMapping(value = "user_delivery_address_updt")
	public String deliveryAddressUpdt(DeliveryAddressDto deliveryAddressDto,DeliveryAddressVo vo) {
		deliveryAddressService.update(deliveryAddressDto, vo);
		return "redirect:user_delivery_address?seq=1";
	}
	@RequestMapping(value = "user_delivery_address_Defupdt")
	public String deliveryAddressDefUpdt(DeliveryAddressDto deliveryAddressDto,@ModelAttribute("vo") DeliveryAddressVo vo) {
//		deliveryAddressService.updateDef(deliveryAddressDto);
		System.out.println("DeliveryAddressVo: " + vo);
		deliveryAddressService.updateDefUsr(deliveryAddressDto,vo);
		return "redirect:user_delivery_address?seq=1";
	}
	@RequestMapping(value = "user_account")
	public String userAccount(Model model, MemberDto memberDto) {
		model.addAttribute("item", memberService.selectOne(memberDto));
		System.out.println("seq: "+memberService.selectOne(memberDto).getSeq());
		return "/usr/v1/pages/user_account";
	}
	@RequestMapping(value = "user_accountUpdt")
	public String user_accountUpdt(MemberDto memberDto) {
		System.out.println("seq1: "+memberDto.getSeq());
		memberService.update(memberDto);
		return "redirect:index";
	}
//	@RequestMapping(value = "user_account")
//	public String userAccount(Model model, UserDto userDto) {
//		model.addAttribute("item", userService.selectOne(userDto));
//		System.out.println("seq: "+userService.selectOne(userDto).getSeq());
//		return "/usr/v1/pages/user_account";
//	}
//	@RequestMapping(value = "user_accountUpdt")
//	public String user_accountUpdt(UserDto userDto) {
//		System.out.println("seq1: "+userDto.getSeq());
//		userService.update(userDto);
//		return "redirect:index";
//	}
	@RequestMapping(value = "user_order_list")
	public String userOrderList(Model model, MemberDto memberDto) {
		model.addAttribute("member", memberService.selectOne(memberDto));
		return "/usr/v1/pages/user_order_list";
	}
	@RequestMapping(value = "user_password")
	public String userPassword(Model model,MemberDto memberDto) {
		model.addAttribute("item", memberService.selectOne(memberDto));
		return "/usr/v1/pages/user_password";
	}
//	@RequestMapping(value = "user_password")
//	public String userPassword(Model model,UserDto userDto) {
//		model.addAttribute("item", userService.selectOne(userDto));
//		return "/usr/v1/pages/user_password";
//	}
	
	@RequestMapping(value = "product_detail")
	public String productDetail(Model model,ProductDto productDto,@ModelAttribute("vo") ProductVo vo,AuthorVo authorVo,ProductAuthorDto productAuthorDto) {
//		List<ProductDto> prodLists = productService.prodList();
//		for(ProductDto prodList:prodLists) {
//			System.out.println("SEQ: "+prodList.getSeq()+" , 제목: "+prodList.getTitle());
//		}
		System.out.println("SEQ: "+productService.prodOne(productDto).getSeq()+" , 제목: "+productService.prodOne(productDto).getTitle());
		model.addAttribute("product", productService.prodUsrOne(vo));
		model.addAttribute("authors", authorService.authorUsrList(authorVo));
//		model.addAttribute("author", authorService.authorOne(authorDto));
//		model.addAttribute("authors", productAuthorService.productAuthorSelected(productAuthorDto));
		return "usr/v1/pages/product_detail";
	}
	@RequestMapping(value = "product_list")
	public String productList(Model model,@ModelAttribute("vo") ProductVo vo) {
		vo.setParamsPaging(productService.listCount(vo));
		System.out.println("get: "+vo.getMakeDateFillter());
		model.addAttribute("list", productService.usrProdList(vo));
		model.addAttribute("bages", codeService.bageList());
//		List<ProductDto> prods = productService.usrProdList(vo);
//		for(ProductDto prod : prods) {
//			System.out.println("출판사: "+prod.getPublisherName());
//			System.out.println("타입: "+prod.getTitle()	);
//		}
			
		System.out.println("최소: "+vo.getMinPrice());
		System.out.println("베스트: " +vo.getBestNy());
		System.out.println("투데이: " +vo.getTodayPickNy());
		System.out.println("타입: " +vo.getProdType());
		System.out.println("최대: "+vo.getMaxPrice());
		return "/usr/v1/pages/product_list";
	}
	
	@RequestMapping(value = "product_buy")
	public String productBuy(Model model, DeliveryAddressDto deliveryAddressDto) {
		model.addAttribute("item", deliveryAddressService.selectDefOne(deliveryAddressDto));
		return "/usr/v1/pages/product_buy";
	}
	@RequestMapping(value = "account_recovery")
	public String accountRecovery() {
		
		return "/usr/v1/pages/account_recovery";
	}
}
