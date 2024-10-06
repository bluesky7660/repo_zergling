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
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.common.util.DateUtil;
import com.exion.infra.code.CodeDto;
import com.exion.infra.code.CodeService;
import com.exion.infra.member.MemberDto;
import com.exion.infra.member.MemberService;
import com.exion.infra.util.Constants;

import jakarta.servlet.http.HttpSession;

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
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value = "index")
	public String index() {
		System.out.println("index");
		return "/usr/v1/pages/index";
	}
	@RequestMapping(value = "login")
	public String login() {
		System.out.println("login");
		return "/usr/v1/pages/login";
	}
	
	@ResponseBody
	@RequestMapping(value = "loginUsrProc")
	public Map<String, Object> loginUsrProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		System.out.println("loginUsrProc");
		Map<String, Object> returnMap = new HashMap<>();
		MemberDto rtMember = memberService.selectUsrOne(memberDto);
		System.out.println("rtMember: " + rtMember);
		
		if (rtMember != null) {
			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqXdm", rtMember.getSeq());
			httpSession.setAttribute("sessIdXdm", rtMember.getUserId());
			httpSession.setAttribute("sessNameXdm", rtMember.getName());
			if(httpSession.getAttribute("prevPage") != null) {
//				returnMap.put("rtp", "buy");
				System.out.println("주소: " + httpSession.getAttribute("prevPage"));
				System.out.println("구매성공");
			}else {
//				returnMap.put("rtp", "fail");
				System.out.println("주소: " + httpSession.getAttribute("prevPage"));
				System.out.println("구매실패");
			}
			
			System.out.println("성공");
			returnMap.put("rt", "success");

		} else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "logoutUsrProc")
	public Map<String, Object> logoutUsrProc(HttpSession httpSession) throws Exception {
		System.out.println("logoutUsrProc");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		httpSession.invalidate();
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		System.out.println("signup");
		model.addAttribute("genders", codeService.genderList());
		return "/usr/v1/pages/signup";
	}
	@RequestMapping(value = "signupInst")
	public String signupInst(MemberDto memberDto) {
		System.out.println("signupInst");
		memberService.insertUsr(memberDto);
		return "redirect:login";
	}
//	@RequestMapping(value = "signupInst")
//	public String signupInst(UserDto userDto) {
//		userService.insertUser(userDto);
//		return "redirect:login";
//	}
	@RequestMapping(value = "user_delivery_address")
	public String deliveryAddress(Model model,HttpSession httpSession, MemberDto memberDto, DeliveryAddressDto deliveryAddressDto,@ModelAttribute("vo") DeliveryAddressVo vo) {
		memberDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		deliveryAddressDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		vo.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		model.addAttribute("member", memberService.selectOne(memberDto));
		model.addAttribute("count", deliveryAddressService.listCount(vo));
		model.addAttribute("item", deliveryAddressService.selectDefOne(deliveryAddressDto));
//		System.out.println("DefSeq: "+deliveryAddressService.selectDefOne(deliveryAddressDto).getSeq());
		model.addAttribute("addrList", deliveryAddressService.selectList(vo));
//		int size = deliveryAddressService.selectList().size();
//		model.addAttribute("size", size); // 사이즈 추가
		System.out.println("user_delivery_address");
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
		System.out.println("user_delivery_address_add");
		return "/usr/v1/pages/user_delivery_address_add";
	}
	@RequestMapping(value = "user_delivery_address_inst")
	public String deliveryAddressInst(DeliveryAddressDto deliveryAddressDto) {
		deliveryAddressService.insertAddr(deliveryAddressDto);
		System.out.println("user_delivery_address_inst");
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
	public String userAccount(Model model, MemberDto memberDto,CodeDto codeDto,HttpSession httpSession) {
		System.out.println("sessSeqXdm: " + httpSession.getAttribute("sessSeqXdm"));
		System.out.println("sessIdXdm: " + httpSession.getAttribute("sessIdXdm"));
		System.out.println("sessNameXdm: " + httpSession.getAttribute("sessNameXdm"));
		memberDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		model.addAttribute("item", memberService.selectOne(memberDto));
//		System.out.println("seq: "+memberService.selectOne(memberDto).getSeq());
//		for(CodeDto item:codeService.genderList()) {
//			System.out.println("코드이름: "+item.getCodeName());
//		}
		model.addAttribute("gender", codeService.genderList());
//		System.out.println("seq: "+.get());
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
	public String userOrderList(Model model, HttpSession httpSession, OrderDto orderDto, MemberDto memberDto) {
//		model.addAttribute("member", memberService.selectOne(memberDto));
		orderDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		model.addAttribute("orderlist", orderService.selectUsrList(orderDto));
//		System.out.println("user_order_list");
		return "/usr/v1/pages/user_order_list";
	}
	@RequestMapping(value = "user_password")
	public String userPassword(Model model,HttpSession httpSession, MemberDto memberDto) {
		memberDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		model.addAttribute("item", memberService.selectOne(memberDto));
		System.out.println("user_password");
		return "/usr/v1/pages/user_password";
	}
//	@RequestMapping(value = "user_password")
//	public String userPassword(Model model,UserDto userDto) {
//		model.addAttribute("item", userService.selectOne(userDto));
//		return "/usr/v1/pages/user_password";
//	}
	
	@RequestMapping(value = "product_detail")
	public String productDetail(Model model, ReviewVo reviewVo, ReviewDto reviewDto, ProductDto productDto,@ModelAttribute("vo") ProductVo vo,AuthorVo authorVo,ProductAuthorDto productAuthorDto) {
		List<ReviewDto> lists = reviewService.selectUsrList(reviewDto);
		for(ReviewDto list:lists) {
			System.out.println("점수: "+list.getRvScore()+" , 이름: "+list.getRvName());
		}
		System.out.println("SEQ: "+productService.prodOne(productDto).getSeq()+" , 제목: "+productService.prodOne(productDto).getTitle());
		model.addAttribute("product", productService.prodUsrOne(vo));
		model.addAttribute("prodAuthor", authorService.prodAuthorList(authorVo));
		model.addAttribute("authors", authorService.authorUsrList(authorVo));
		model.addAttribute("rvTags", codeService.tagsList());
		model.addAttribute("rvCount", reviewService.listCount(reviewVo));
		model.addAttribute("rvList", reviewService.selectUsrList(reviewDto));
		System.out.println("rvCount:" +reviewService.listCount(reviewVo));
		System.out.println("목차: "+productService.prodOne(productDto).getIntro());
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
	public String productBuy(Model model, DeliveryAddressDto deliveryAddressDto,ProductVo productVo, DeliveryAddressVo addressVo, HttpSession session) {
		
		String mmSeq = (String) session.getAttribute("sessSeqXdm");
		deliveryAddressDto.setMember_seq(mmSeq);
		model.addAttribute("user", deliveryAddressService.selectDefOne(deliveryAddressDto));
		model.addAttribute("item", productService.prodUsrOne(productVo));
		Date deliveryDate = DateUtil.getDeliveryDate(2);
		model.addAttribute("deliveryDate",deliveryDate);
		model.addAttribute("addrList", deliveryAddressService.selectList(addressVo));
		System.out.println("product_buy");
		return "/usr/v1/pages/product_buy";
	}
	@RequestMapping(value = "account_recovery")
	public String accountRecovery() {
		System.out.println("account_recovery");
		return "/usr/v1/pages/account_recovery";
	}
}
