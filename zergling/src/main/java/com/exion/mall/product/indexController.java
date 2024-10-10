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
	public String index(Model model, ProductVo productVo) {
		model.addAttribute("newProd", productService.newProdList(productVo));
		model.addAttribute("bestProd", productService.bestProdList(productVo));
		model.addAttribute("mdPickProd", productService.mdPickProdList(productVo));
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
			String prevPage = (String) httpSession.getAttribute("prevPage");
			httpSession.removeAttribute("prevPage"); 
			returnMap.put("redirectUrl", prevPage != null ? prevPage : "/index");
			
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
	public String userOrderList(Model model, HttpSession httpSession, OrderDto orderDto, MemberDto memberDto,ProductVo productVo) {
//		model.addAttribute("member", memberService.selectOne(memberDto));
		orderDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		
		model.addAttribute("orderlist", orderService.selectUsrList(orderDto));
		System.out.println("orderDto:"+orderDto.getProduct_seq());
		System.out.println("selectUsrList:"+orderService.selectUsrList(orderDto).get(0).getDeliveryDate()+", "+orderService.selectUsrList(orderDto).get(0).getOrderNumber());
		productVo.setSeq(orderService.selectUsrList(orderDto).get(0).getProduct_seq());
		model.addAttribute("prod", productService.prodUsrOne(productVo));
		System.out.println("user_order_list");
		return "usr/v1/pages/user_order_list";
	}
//	@RequestMapping(value = "orderinst")
//	public String orderinst(Model model, HttpSession httpSession, OrderDto orderDto, MemberDto memberDto) {
//		
//		return "redirect:user_order_list";
//	}
	
	@ResponseBody
	@RequestMapping(value = "orderinst")
	public Map<String, Object> orderinst(OrderDto orderDto){
//		orderDto.setProduct_seq(orderDto.getSeq()); 
		System.out.println("orderinst");
		System.out.println("상품seq:"+orderDto.getProduct_seq());
		System.out.println("유저seq:"+orderDto.getMmSeq());
		System.out.println("주소seq:"+orderDto.getDaSeq());
		Integer rtOrder = orderService.insert(orderDto);
		
		Map<String, Object> returnMap = new HashMap<>();
		if (rtOrder != null) {
			System.out.println("성공");
			returnMap.put("rt", "success");
		} else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		return returnMap;
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
	public String productDetail(Model model,@ModelAttribute("vo") ReviewVo reviewVo, ReviewDto reviewDto, ProductDto productDto,ProductVo vo,AuthorVo authorVo,ProductAuthorDto productAuthorDto) {
		reviewVo.setParamsPaging(reviewService.listCount(reviewVo));
		List<ReviewDto> lists = reviewService.selectUsrList(reviewVo);
		for(ReviewDto list:lists) {
			System.out.println("점수: "+list.getRvScore()+" , 이름: "+list.getRvName());
		}
		System.out.println("SEQ: "+productService.prodOne(productDto).getSeq()+" , 제목: "+productService.prodOne(productDto).getTitle());
		model.addAttribute("product", productService.prodUsrOne(vo));
		model.addAttribute("prodAuthor", authorService.prodAuthorList(authorVo));
		model.addAttribute("authors", authorService.authorUsrList(authorVo));
		model.addAttribute("rvTags", codeService.tagsList());
		model.addAttribute("rvCount", reviewService.listCount(reviewVo));
		model.addAttribute("rvList", reviewService.selectUsrList(reviewVo));
		System.out.println("rvCount:" +reviewService.listCount(reviewVo));
		System.out.println("목차: "+productService.prodOne(productDto).getIntro());
//		model.addAttribute("author", authorService.authorOne(authorDto));
//		model.addAttribute("authors", productAuthorService.productAuthorSelected(productAuthorDto));
		return "usr/v1/pages/product_detail";
	}
	@RequestMapping(value = "product_list")
	public String productList(Model model,@ModelAttribute("vo") ProductVo productVo,ReviewVo reviewVo) {
		productVo.setParamsPaging(productService.listCount(productVo));
		System.out.println("get: "+productVo.getMakeDateFillter());
		model.addAttribute("list", productService.usrProdList(productVo));
		model.addAttribute("bages", codeService.bageList());
//		List<ProductDto> prods = productService.usrProdList(productVo);
//		for(ProductDto prod : prods) {
//			System.out.println("출판사: "+prod.getPublisherName());
//			System.out.println("타입: "+prod.getTitle()	);
//		}
//		System.out.println("리뷰점수: " +reviewService.totalNum(reviewVo));
		System.out.println("최소: "+productVo.getMinPrice());
		System.out.println("베스트: " +productVo.getBestNy());
		System.out.println("투데이: " +productVo.getTodayPickNy());
		System.out.println("타입: " +productVo.getProdType());
		System.out.println("최대: "+productVo.getMaxPrice());
		return "/usr/v1/pages/product_list";
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
			System.out.println("usr.daseq: " +deliveryAddressService.selectDefOne(deliveryAddressDto).getDaSeq());
			model.addAttribute("user", deliveryAddressService.selectDefOne(deliveryAddressDto));
		}
//		model.addAttribute("user", deliveryAddressService.selectDefOne(deliveryAddressDto));
		model.addAttribute("item", productService.prodUsrOne(productVo));
		Date deliveryDate = DateUtil.getDeliveryDate(2);
		model.addAttribute("deliveryDate",deliveryDate);
		model.addAttribute("addrList", deliveryAddressService.selectList(addressVo));
		System.out.println("product_buy");
		return "/usr/v1/pages/product_buy";
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
		System.out.println("상품seq: "+seq);
		
		return returnMap;
	}
	@RequestMapping(value = "account_recovery")
	public String accountRecovery() {
		System.out.println("account_recovery");
		return "/usr/v1/pages/account_recovery";
	}
}
