package com.exion.mall.product;



import java.awt.Font;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.common.util.DateUtil;
import com.exion.infra.code.CodeDto;
import com.exion.infra.code.CodeService;
import com.exion.infra.codegroup.BaseVo;
import com.exion.infra.kakao.KakaoBookService;
import com.exion.infra.kakao.KakaoBookVo;
import com.exion.infra.member.MemberDto;
import com.exion.infra.member.MemberService;
import com.exion.infra.naver.BookController;
import com.exion.infra.naver.BookVo;
//import com.exion.infra.recaptcha.CreateAssessment;
import com.exion.infra.recaptcha.RecaptchaService;
import com.exion.infra.util.Constants;
//import com.wf.captcha.utils.CaptchaUtil;
import com.exion.infra.youtube.YouTubeService;

import io.springboot.captcha.SpecCaptcha;
import io.springboot.captcha.base.Captcha;
import io.springboot.captcha.utils.CaptchaJakartaUtil;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	
	
	@Autowired
	BookController bookController;
	
	@Autowired
    KakaoBookService kakaoBookService;
	
	@Autowired
    YouTubeService youTubeService;
	
	@RequestMapping(value = "/captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    response.setContentType("image/gif");
	    response.setHeader("Pragma", "No-cache");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setDateHeader("Expires", 0);

	    SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
	    specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));
	    specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
//	    System.out.println("specCaptcha.toBase64()"+specCaptcha.toBase64());
//	    System.out.println("specCaptcha.text().toLowerCase()"+specCaptcha.text().toLowerCase());
	    request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());
//	    String base64Image = "data:image/gif;base64,"+specCaptcha.toBase64();

	 // 이미지 출력
//	    System.out.println("출력");
	    specCaptcha.out(response.getOutputStream());
//	    System.out.println("출력성공");
	}
//	@RequestMapping(value = "/refreshCaptcha")
//    public void refreshCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
//        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));
//        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
//        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());
//        specCaptcha.out(response.getOutputStream());
//    }
	@RequestMapping(value = "index")
	public String index(Model model, ProductVo productVo) {
		model.addAttribute("newProd", productService.newProdList(productVo));
		model.addAttribute("bestProd", productService.bestProdList(productVo));
		model.addAttribute("mdPickProd", productService.mdPickProdList(productVo));
		List<BookVo> nvbestSellers = bookController.getBestSellers("베스트셀러");
		model.addAttribute("naverbest", nvbestSellers);
		System.out.println("index끝전");
		List<KakaoBookVo> bestSellers = kakaoBookService.getBestSellers("베스트셀러");
		model.addAttribute("kakaobest", bestSellers);
		System.out.println("index");
		return "/usr/v1/pages/index";
	}
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request,HttpSession httpSession) {
//		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
//		System.out.println("specCaptcha.toBase64()"+specCaptcha.toBase64());
//		System.out.println("specCaptcha.text().toLowerCase()"+specCaptcha.text().toLowerCase());
		String requestURL = request.getRequestURL().toString();
		String refererURL = request.getHeader("referer");
		String queryString = request.getQueryString();
		System.out.println("requestURL: "+ requestURL);
		System.out.println("refererURL: "+ refererURL);
		System.out.println("login_queryString: "+ queryString);
		if(queryString !=null) {
			if (queryString.contains("redirect=review")) {
	            System.out.println("리뷰 작성으로 로그인 성공");
	            httpSession.setAttribute("prevPage",refererURL); // 원하는 URL 설정
	        } 
		}
		
		System.out.println("login");
		return "/usr/v1/pages/login";
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
		model.addAttribute("userPage", "deliveryAddress");
		System.out.println("user_delivery_address");
		return "/usr/v1/pages/user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_addressMfom")
	public String deliveryAddressMfom(Model model,DeliveryAddressDto deliveryAddressDto) {
		model.addAttribute("item", deliveryAddressService.selectOne(deliveryAddressDto));
		model.addAttribute("userPage", "deliveryAddress");
		System.out.println("MfomSeq: "+deliveryAddressService.selectOne(deliveryAddressDto).getSeq());
		return "/usr/v1/pages/user_delivery_addressMfom";
	}
	@RequestMapping(value = "user_delivery_address_add")
	public String deliveryAddressAddForm(Model model) {
		System.out.println("user_delivery_address_add");
		model.addAttribute("userPage", "deliveryAddress");
		return "/usr/v1/pages/user_delivery_address_add";
	}
	@RequestMapping(value = "user_delivery_address_inst")
	public String deliveryAddressInst(DeliveryAddressDto deliveryAddressDto) {
		deliveryAddressService.insertAddr(deliveryAddressDto);
		System.out.println("user_delivery_address_inst");
		return "redirect:user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_address_updt")
	public String deliveryAddressUpdt(DeliveryAddressDto deliveryAddressDto,DeliveryAddressVo vo) {
		deliveryAddressService.update(deliveryAddressDto, vo);
		return "redirect:user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_address_uelt")
	public String deliveryAddressUelt(DeliveryAddressDto deliveryAddressDto) {
		deliveryAddressService.ueleteAddr(deliveryAddressDto);
		return "redirect:user_delivery_address";
	}
	@RequestMapping(value = "user_delivery_address_Defupdt")
	public String deliveryAddressDefUpdt(DeliveryAddressDto deliveryAddressDto,@ModelAttribute("vo") DeliveryAddressVo vo) {
//		deliveryAddressService.updateDef(deliveryAddressDto);
		System.out.println("DeliveryAddressVo: " + vo);
		deliveryAddressService.updateDefUsr(deliveryAddressDto,vo);
		return "redirect:user_delivery_address";
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
		model.addAttribute("userPage", "account");
//		System.out.println("seq: "+.get());
		return "/usr/v1/pages/user_account";
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
		List<OrderDto> orderlist = orderService.selectUsrList(orderDto);
		model.addAttribute("orderlist", orderlist);
		int readyCount = 0;
		int inDeliveryCount = 0;
		int deliveredCount = 0;
		int cancelledCount = 0;
		int paymentDoneCount = 0;
		for(OrderDto order : orderlist) {
			switch (order.getOrderStatus()) {
				case 57: {
					readyCount++;
					break;
				}
				case 14: {
					inDeliveryCount++;
					break;
				}
				case 10: {
					deliveredCount++;
					break;
				}
				case 19: {
					cancelledCount++;
					break;
				}
				case 56: {
					paymentDoneCount++;
					break;
				}
			}
//			default:
//				
//			}
		}
		// 모델에 각 카운트 값 추가
		model.addAttribute("readyCount", readyCount);
		model.addAttribute("inDeliveryCount", inDeliveryCount);
		model.addAttribute("deliveredCount", deliveredCount);
		model.addAttribute("cancelledCount", cancelledCount);
		model.addAttribute("paymentDoneCount", paymentDoneCount);
//		System.out.println("orderDto:"+orderDto.getProduct_seq());
		System.out.println("orderDto:"+orderDto.getSeq());
//		System.out.println("selectUsrList:"+orderService.selectUsrList(orderDto).get(0).getDeliveryDate()+", "+orderService.selectUsrList(orderDto).get(0).getOrderNumber());
//		productVo.setSeq(orderService.selectUsrList(orderDto).get(0).getProduct_seq());
//		model.addAttribute("prod", productService.prodUsrOne(productVo));
		model.addAttribute("userPage", "orderList");
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
		System.out.println("걍seq:"+orderDto.getSeq());
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
	@ResponseBody
	@RequestMapping(value = "orderUelt")
	public String orderUelt(OrderDto orderDto){
		
		return "returnMap";
	}
	@RequestMapping(value = "user_password")
	public String userPassword(Model model,HttpSession httpSession, MemberDto memberDto) {
		memberDto.setSeq(httpSession.getAttribute("sessSeqXdm").toString());
		model.addAttribute("item", memberService.selectOne(memberDto));
		model.addAttribute("userPage", "passwordChange");
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
		model.addAttribute("best", productService.bestCategoryProdList(vo));
		model.addAttribute("prodAuthor", authorService.prodAuthorList(authorVo));
		model.addAttribute("authors", authorService.authorUsrList(authorVo));
		model.addAttribute("rvTags", codeService.tagsList());
		model.addAttribute("rvCount", reviewService.listCount(reviewVo));
		model.addAttribute("rvList", reviewService.selectUsrList(reviewVo));
		System.out.println("rvCount:" +reviewService.listCount(reviewVo));
		System.out.println("목차: "+productService.prodOne(productDto).getIntro());
		System.out.println("리뷰점수: " +reviewService.totalNum(reviewVo));
//		System.out.println("rvTags: " +codeService.tagsList());
//		model.addAttribute("author", authorService.authorOne(authorDto));
//		model.addAttribute("authors", productAuthorService.productAuthorSelected(productAuthorDto));
		return "/usr/v1/pages/product_detail";
	}
	@RequestMapping(value = "product_list")
	public String productList(Model model,@ModelAttribute("vo") ProductVo productVo,@ModelAttribute("shVo") BaseVo shVo,ReviewVo reviewVo) {
		productVo.setParamsPaging(productService.listCount(productVo));
		System.out.println("get: "+productVo.getMakeDateFillter());
		System.out.println("검색어: "+productVo.getSearchKeyword());
//		
		model.addAttribute("list", productService.usrProdList(productVo));
		model.addAttribute("bages", codeService.bageList());
		model.addAttribute("reviewStats", reviewService.listScore(reviewVo, productVo));
//		model.addAttribute("rvNum", reviewService.totalNum(reviewVo));
		//		List<ProductDto> prods = productService.usrProdList(productVo);
//		for(ProductDto prod : prods) {
//			System.out.println("상품seq: "+prod.getSeq());
//			System.out.println("타입: "+prod.getTitle()	);
//		}
		System.out.println("검색: "+productService.usrProdList(productVo));
		System.out.println("리뷰점수: " +reviewService.totalNum(reviewVo));
//		System.out.println("최소: "+productVo.getMinPrice());
//		System.out.println("베스트: " +productVo.getBestNy());
//		System.out.println("투데이: " +productVo.getTodayPickNy());
//		System.out.println("타입: " +productVo.getProdType());
//		System.out.println("최대: "+productVo.getMaxPrice());
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
			System.out.println("usr.daseq: " +(deliveryAddressService.selectDefOne(deliveryAddressDto) != null ? deliveryAddressService.selectDefOne(deliveryAddressDto).getDaSeq(): "null"));
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
//		System.out.println("상품seq: "+seq);
		
		return returnMap;
	}
	@ResponseBody
	@RequestMapping(value = "reviewInst")
	public Map<String, Object> reviewInst(ReviewDto reviewDto){
//		System.out.println("상품seq:"+reviewDto.getProduct_seq());
		Integer rtReview = reviewService.insert(reviewDto);
//		productService.totalNum(reviewDto);
		Map<String, Object> returnMap = new HashMap<>();
		if(rtReview != null) {
			System.out.println("성공");
			
			returnMap.put("rt", "success");
		}else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	@ResponseBody
	@RequestMapping(value = "reviewUelt")
	public Map<String, Object> reviewUelt(ReviewDto reviewDto){
		Integer rtUelete = reviewService.uelete(reviewDto);
		Map<String, Object> returnMap = new HashMap<>();
		if(rtUelete != null) {
			System.out.println("성공");
			
			returnMap.put("rt", "success");
		}else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
//	@ResponseBody
//	@RequestMapping(value = "RefreshReviews")
//    public List<ReviewDto> RefreshReviews(@RequestBody ReviewVo reviewVo) {
//		reviewVo.setSeq(reviewVo.getProduct_seq());
//		reviewVo.setThisPage(1);
//		System.out.println("현재 페이지:"+reviewVo.getThisPage());
////        return reviewService.selectUsrList(reviewVo); // 제품에 대한 리뷰 목록 반환
//		return reviewService.selectUsrList(reviewVo);
//    }
//	@ResponseBody
//	@RequestMapping(value = "RefreshReviews")
//    public Map<String, Object> RefreshReviews(@RequestBody ReviewVo reviewVo) {
//		reviewVo.setSeq(reviewVo.getProduct_seq());
//		reviewVo.setThisPage(1);
//		reviewVo.setParamsPaging(reviewService.listCount(reviewVo));
//		System.out.println("상품seq:"+reviewVo.getSeq());
//		System.out.println("상품thisPage:"+reviewVo.getThisPage());
//		List<ReviewDto> reviews = reviewService.selectUsrList(reviewVo);
//
//	    Map<String, Object> responseMap = new HashMap<>();
//	    responseMap.put("rvList", reviews); // 리뷰 목록 추가
//	    responseMap.put("thisPage", reviewVo.getThisPage()); // 현재 페이지
//	    responseMap.put("totalPages", reviewVo.getTotalPages());
////        return reviewService.selectUsrList(reviewVo); // 제품에 대한 리뷰 목록 반환
//		return responseMap;
//    }
	@ResponseBody
	@RequestMapping(value = "RefreshReviews")
    public Map<String, Object> RefreshReviews(@RequestBody ReviewVo reviewVo, HttpSession session, ProductDto productDto) {
		System.out.println("RefreshReviews");
		System.out.println("리뷰컨트롤sort:"+reviewVo.getRvSort());
		reviewVo.setSeq(reviewVo.getProduct_seq());
		reviewVo.setThisPage(1);
		reviewVo.setParamsPaging(reviewService.listCount(reviewVo));
//		System.out.println("상품seq:"+reviewVo.getSeq());
//		System.out.println("상품thisPage:"+reviewVo.getThisPage());
		List<ReviewDto> reviewsList = reviewService.selectUsrList(reviewVo);

	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("rvList", reviewsList); // 리뷰 목록 추가
	    responseMap.put("thisPage", reviewVo.getThisPage()); // 현재 페이지
	    responseMap.put("totalPages", reviewVo.getTotalPages());
	    responseMap.put("sessSeqXdm", session.getAttribute("sessSeqXdm"));
		System.out.println("상품seq:"+reviewVo.getSeq());
		System.out.println("상품thisPage:"+reviewVo.getThisPage());
		System.out.println("세션seq:"+session.getAttribute("sessSeqXdm"));
		System.out.println("세션이름:"+session.getAttribute("sessNameXdm"));
		
		List<ReviewDto> reviews = reviewService.listAll(reviewVo);

	    double totalScore = 0;
	    int reviewCount = reviewService.listCount(reviewVo);
	    Map<Double, Integer> scoreCounts = new LinkedHashMap<>();
	    scoreCounts.put(10.0, 0);
	    scoreCounts.put(7.5, 0);
	    scoreCounts.put(5.0, 0);
	    scoreCounts.put(2.5, 0);
	    scoreCounts.put(0.0, 0);
//	    Map<Integer, Integer> tagCounts = new HashMap<>();
	    Map<String, Integer> tagCounts = new LinkedHashMap<>();
	    tagCounts.put("고마워요", 0);
	    tagCounts.put("최고예요", 0);
	    tagCounts.put("공감돼요", 0);
	    tagCounts.put("재밌어요", 0);
	    tagCounts.put("힐링돼요", 0);
	 // 태그 번호와 이름을 저장할 맵
	    Map<Integer, String> tagNames = new HashMap<>();
	    String mostSelectedTag = "";
	    int mostSelectedTagNumber = 0;
	    int maxCount = 0;

	    for (ReviewDto review : reviews) {
	        double score = review.getRvScore();
	        totalScore += score;

	        scoreCounts.put(score, scoreCounts.getOrDefault(score, 0) + 1);

	        String tagName = review.getRvSelectTagName(); // 각 리뷰의 단일 태그

	        int tagNumber = review.getRvSelectTag(); // 태그 번호 가져오기
	        tagNames.putIfAbsent(tagNumber, tagName);
	        // 태그 개수 업데이트
	        if (tagCounts.containsKey(tagName)) { // 미리 초기화된 태그 목록에 존재하는지 확인
	            tagCounts.put(tagName, tagCounts.get(tagName) + 1);
	        }
	        
	    }
	    
	    for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
	    	String tagName = entry.getKey();
	        int count = entry.getValue();

	        // 가장 많이 선택된 태그 업데이트
	        if (count > maxCount) {
	            maxCount = count;
//	            mostSelectedTagNumber = tagName; // 가장 많이 선택된 태그의 번호
	            mostSelectedTag = tagName; // 번호로 태그 이름 찾기
	        }
	    }

	    double averageScore = reviewCount > 0 ? totalScore / reviewCount : 0;
	    averageScore = Math.round(averageScore* 10) / 10.0;
	    
	    productDto.setReviewNum(averageScore);
	    productDto.setSeq(reviewVo.getSeq());
	    System.out.println("상품seq2:"+productDto.getSeq());
	    productService.reviewNum(productDto);
	    
	    responseMap.put("averageScore", averageScore);
	    responseMap.put("scoreCounts", scoreCounts);
	    responseMap.put("reviewCount", reviewCount);
	    responseMap.put("tagCounts", tagCounts);
	    responseMap.put("mostSelectedTag", mostSelectedTag);
//	    responseMap.put("totalReviews", mostSelectedTagNumber);
	    System.out.println("averageScore:"+averageScore);
        System.out.println("scoreCounts:"+scoreCounts);
        System.out.println("reviewCount:"+reviewCount);
        System.out.println("tagCounts:"+tagCounts);
        System.out.println("mostSelectedTag:"+mostSelectedTag);
//        return reviewService.selectUsrList(reviewVo); // 제품에 대한 리뷰 목록 반환
		return responseMap;
    }
	@ResponseBody
	@RequestMapping(value = "reviewList", method = RequestMethod.POST)
    public Map<String, Object> reviewList(@RequestBody ReviewVo reviewVo) {
		System.out.println("reviewList");
//		reviewVo.setThisPage(thisPage);
		reviewVo.setParamsPaging(reviewService.listCount(reviewVo));
		System.out.println("상품seq:"+reviewVo.getSeq());
		System.out.println("상품thisPage:"+reviewVo.getThisPage());
		List<ReviewDto> reviews = reviewService.selectUsrList(reviewVo);

	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("rvList", reviews); // 리뷰 목록 추가
	    responseMap.put("thisPage", reviewVo.getThisPage()); // 현재 페이지
	    responseMap.put("totalPages", reviewVo.getTotalPages()); // 총 페이지 수
        return responseMap; // 제품에 대한 리뷰 목록 반환
    }
	@ResponseBody
	@RequestMapping(value = "reviewAverage")
    public Map<String, Object> reviewAverage(@RequestBody ReviewVo reviewVo) {
		System.out.println("reviewAverage");
		reviewVo.setParamsPaging(reviewService.listCount(reviewVo));
		System.out.println("상품seq:"+reviewVo.getSeq());
		System.out.println("상품thisPage:"+reviewVo.getThisPage());
		
		List<ReviewDto> reviews = reviewService.listAll(reviewVo);

	    double totalScore = 0;
	    int reviewCount = reviewService.listCount(reviewVo);
	    Map<Double, Integer> scoreCounts = new LinkedHashMap<>();
	    scoreCounts.put(10.0, 0);
	    scoreCounts.put(7.5, 0);
	    scoreCounts.put(5.0, 0);
	    scoreCounts.put(2.5, 0);
	    scoreCounts.put(0.0, 0);
//	    Map<Integer, Integer> tagCounts = new HashMap<>();
	    Map<String, Integer> tagCounts = new LinkedHashMap<>();
	    tagCounts.put("고마워요", 0);
	    tagCounts.put("최고예요", 0);
	    tagCounts.put("공감돼요", 0);
	    tagCounts.put("재밌어요", 0);
	    tagCounts.put("힐링돼요", 0);
	 // 태그 번호와 이름을 저장할 맵
	    Map<Integer, String> tagNames = new HashMap<>();
	    String mostSelectedTag = "";
	    int mostSelectedTagNumber = 0;
	    int maxCount = 0;

	    for (ReviewDto review : reviews) {
	        double score = review.getRvScore();
	        totalScore += score;

	        scoreCounts.put(score, scoreCounts.getOrDefault(score, 0) + 1);

	        String tagName = review.getRvSelectTagName(); // 각 리뷰의 단일 태그

	        int tagNumber = review.getRvSelectTag(); // 태그 번호 가져오기
	        tagNames.putIfAbsent(tagNumber, tagName);
	        // 태그 개수 업데이트
	        if (tagCounts.containsKey(tagName)) { // 미리 초기화된 태그 목록에 존재하는지 확인
	            tagCounts.put(tagName, tagCounts.get(tagName) + 1);
	        }
	        
	    }
	    
	    for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
	    	String tagName = entry.getKey();
	        int count = entry.getValue();

	        // 가장 많이 선택된 태그 업데이트
	        if (count > maxCount) {
	            maxCount = count;
//	            mostSelectedTagNumber = tagName; // 가장 많이 선택된 태그의 번호
	            mostSelectedTag = tagName; // 번호로 태그 이름 찾기
	        }
	    }

	    double averageScore = reviewCount > 0 ? totalScore / reviewCount : 0;
	    averageScore = Math.round(averageScore* 10) / 10.0;
	    
		Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("rvList", reviews); // 리뷰 목록 추가
	    responseMap.put("averageScore", averageScore);
	    responseMap.put("scoreCounts", scoreCounts);
	    responseMap.put("reviewCount", reviewCount);
	    responseMap.put("tagCounts", tagCounts);
	    responseMap.put("mostSelectedTag", mostSelectedTag);
//	    responseMap.put("totalReviews", mostSelectedTagNumber);
	    System.out.println("averageScore:"+averageScore);
        System.out.println("scoreCounts:"+scoreCounts);
        System.out.println("reviewCount:"+reviewCount);
        System.out.println("tagCounts:"+tagCounts);
        System.out.println("mostSelectedTag:"+mostSelectedTag);
//        System.out.println("totalReviews:"+totalReviews);
//        System.out.println("mostSelectedTagNumber:"+mostSelectedTagNumber);
		return responseMap;
	}
	@ResponseBody
	@RequestMapping(value = "reviewSort")
    public Map<String, Object> reviewSort() {

	    Map<String, Object> responseMap = new HashMap<>();

        return responseMap; // 제품에 대한 리뷰 목록 반환
    }
	@ResponseBody
	@RequestMapping(value = "sortSelect")
    public Map<String, Object> sortSelect() {

	    Map<String, Object> responseMap = new HashMap<>();

        return responseMap; // 제품에 대한 리뷰 목록 반환
    }
	@RequestMapping(value = "account_recovery")
	public String accountRecovery() {
		System.out.println("account_recovery");
		return "/usr/v1/pages/account_recovery";
	}
	@RequestMapping(value = "book_review_video_list")
	public String videoList(@ModelAttribute("vo") BaseVo baseVo) {
		
		System.out.println("book_review_video_list");
		return "/usr/v1/pages/book_review_video_list";
	}
	@RequestMapping(value = "channels_video_list")
	public String channelsVideoList(Model model, @ModelAttribute("vo") BaseVo baseVo) {
		model.addAttribute("channels", youTubeService.channelSelectAllList());
		System.out.println("channels_video_list");
		return "/usr/v1/pages/channels_video_list";
	}
//	@RequestMapping(value = "instaProfile")
//	public String instaProfile() {
//		
//		System.out.println("instaProfile Page Accessed");
//		return "/usr/v1/pages/profile";
//	}
}
