package com.exion.infra.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.infra.kakao.login.KakaoLoginService;
import com.exion.infra.mail.MailService;
import com.exion.infra.recaptcha.RecaptchaService;
import com.exion.infra.util.Constants;

import io.springboot.captcha.utils.CaptchaJakartaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
    private RecaptchaService recaptchaService;
	
	@Autowired
	KakaoLoginService kakaoLoginService;
	
	@RequestMapping(value = "/v1/infra/member/memberXdmList")
	public String memberXdmList(Model model,@ModelAttribute("vo") MemberVo vo) {
		vo.setParamsPaging(memberService.listCount(vo));
		model.addAttribute("list3", memberService.selectXdmMember(vo)) ;
		System.out.println("1: "+vo.getDateOfBirth());
		return "xdm/v1/infra/member/memberXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/member/memberXdmForm")
	public String memberXdmForm() {

		return "xdm/v1/infra/member/memberXdmForm";
	}
	@RequestMapping(value = "/v1/infra/member/memberXdmInst")
	public String memberXdmForm(MemberDto memberDto) {
//		System.out.println("그룹이름: "+memberDto.getDateOfBirth());	
		memberDto.setUserPassword(encodeBcrypt(memberDto.getUserPassword(), 10));
		memberService.insert(memberDto);
		mailService.sendMailSimple();
		return "redirect:/v1/infra/member/memberXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/member/memberXdmMfom")
	public String memberXdmMFom(MemberDto memberDto,Model model) {
		
		model.addAttribute("item", memberService.selectOne(memberDto));
		return "xdm/v1/infra/member/memberXdmMfom";
	}
	@RequestMapping(value = "/v1/infra/member/memberXdmUpdt")
	public String memberXdmUpdt(MemberDto memberDto) {
//		memberDto.setUserPassword(encodeBcrypt(memberDto.getUserPassword(), 10));
		memberService.update(memberDto);
		return "redirect:/v1/infra/member/memberXdmList";
	}
	@RequestMapping(value = "/v1/infra/member/loginXdm")
	public String loginXdm() {
		return "xdm/v1/infra/member/memberXdmLogin";
	}
	@RequestMapping(value = "/v1/infra/member/signupXdm")
	public String signupXdm() {
		return "xdm/v1/infra/member/memberXdmSignup";
	}
	
	public String encodeBcrypt(String planeText, int strength) {
		  return new BCryptPasswordEncoder(strength).encode(planeText);
	}

			
	public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
	  return passwordEncoder.matches(planeText, hashValue);
	}
	
	@ResponseBody
	@RequestMapping(value = "v1/infra/member/loginXdmProc")
	public Map<String, Object> loginXdmProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<>();
		MemberDto rtMember = memberService.selectXdmOne(memberDto);
		
		if (rtMember != null) {
			if(matchesBcrypt(memberDto.getUserPassword(), rtMember.getUserPassword(), 10)) {
	//			MemberDto rtMember2 = service.selectOneLogin(dto);
				httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
				httpSession.setAttribute("sessSeqXdm", rtMember.getSeq());
				httpSession.setAttribute("sessIdXdm", rtMember.getUserId());
				httpSession.setAttribute("sessNameXdm", rtMember.getName());
				System.out.println("성공");
				returnMap.put("rt", "success");
			}else {
				returnMap.put("rt", "fail");
			}

		} else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/v1/infra/member/logoutXdmProc")
	public Map<String, Object> logoutXdmProc(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		httpSession.invalidate();
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	//user
	@RequestMapping(value = "signupInst")
	@ResponseBody
	public Map<String, Object> signupInst(MemberDto memberDto,@RequestParam("captchaCode") String captchaCode, HttpServletRequest request) {
		System.out.println("signupInst");
		System.out.println("test:"+memberDto.getName());
		
		Map<String, Object> returnMap = new HashMap<>();
		String sessionCode = (String) request.getSession().getAttribute("captcha");
//		System.out.println("captchaCode:"+captchaCode);
//		System.out.println("request:"+sessionCode);
//		System.out.println("CaptchaJakartaUtil.ver(captchaCode, request):"+CaptchaJakartaUtil.ver(captchaCode, request));
		if (!CaptchaJakartaUtil.ver(captchaCode, request)) {
            returnMap.put("rt", "fail");
            returnMap.put("message", "Captcha validation failed.");
            System.out.println("Captcha실패");

            return returnMap;
        }
        // 캡차 세션 제거
		CaptchaJakartaUtil.clear(request);
		System.out.println("Captcha성공");
		memberDto.setUserPassword(encodeBcrypt(memberDto.getUserPassword(), 10));
		memberService.insertUsr(memberDto);
		returnMap.put("rt", "success");
		return returnMap;
	}
	@RequestMapping(value = "user_accountUpdt")
	public String userAccountUpdt(MemberDto memberDto) {
//		System.out.println("seq1: "+memberDto.getSeq());
		memberService.update(memberDto);
		return "redirect:index";
	}
	@RequestMapping(value = "user_accountPwUpdt")
	public String user_accountPwUpdt(MemberDto memberDto) {
//		System.out.println("seq1: "+memberDto.getSeq());
		memberDto.setUserPassword(encodeBcrypt(memberDto.getUserPassword(), 10));
		memberService.updatePW(memberDto);
		return "redirect:index";
	}
	@ResponseBody
	@RequestMapping(value = "loginUsrProc")
	public Map<String, Object> loginUsrProc(@RequestParam("captchaCode") String captchaCode, 
//			@RequestParam("token") String token,
			//@RequestParam("recaptchaAction") String recaptchaAction, 
			MemberDto memberDto, HttpSession httpSession,HttpServletRequest request) throws Exception {
		
		System.out.println("loginUsrProc");
		Map<String, Object> returnMap = new HashMap<>();
		// EasyCaptcha 검증
		String sessionCode = (String) request.getSession().getAttribute("captcha");
		System.out.println("captchaCode:"+captchaCode);
		System.out.println("request:"+sessionCode);
		System.out.println("CaptchaJakartaUtil.ver(captchaCode, request):"+CaptchaJakartaUtil.ver(captchaCode, request));
		if (!CaptchaJakartaUtil.ver(captchaCode, request)) {
            returnMap.put("rt", "fail");
            returnMap.put("message", "Captcha validation failed.");
            System.out.println("Captcha실패");
             // 캡차 세션 제거
//            refreshCaptcha();
            return returnMap;
        }
		CaptchaJakartaUtil.clear(request);
		System.out.println("Captcha성공");
//		try {
//			Properties properties = new Properties();
//		    Resource resource = new ClassPathResource("application.properties");
//		    try (InputStream input = resource.getInputStream()) {
//		        properties.load(input);
//		    }
//			
//			// 설정 파일에서 값 읽기
////			String projectID = properties.getProperty("google.recaptcha.projectID");
////			String recaptchaKey = properties.getProperty("google.recaptcha.key"); // 설정 파일에서 읽어오거나 상수로 관리
//////			String serviceAccountKeyFilePath = properties.getProperty("google.service.account.key.file");
////	        System.out.println("projectID:"+projectID);
////		    System.out.println("recaptchaKey:"+recaptchaKey);
////		    System.out.println("token:"+token);
////		    System.out.println("recaptchaAction:"+recaptchaAction);
//////		    System.out.println("serviceAccountKeyFilePath:"+serviceAccountKeyFilePath);
//		  //reCAPTCHA 검증
//            boolean isValid = recaptchaService.verifyRecaptcha(token,recaptchaAction);
//            if (!isValid) {
//                returnMap.put("rt", "fail");
//                returnMap.put("message", "reCAPTCHA validation failed.");
//                return returnMap;
//            }
////          
//            
////		    CreateAssessment.createAssessment(serviceAccountKeyFilePath, projectID, recaptchaKey, token, recaptchaAction);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        returnMap.put("rt", "fail");
//	        return returnMap; // reCAPTCHA 검증 실패
//	    }
		
		MemberDto rtMember = memberService.selectUsrOne(memberDto);
		System.out.println("rtMember: " + rtMember);
		if (rtMember != null) {
			if(matchesBcrypt(memberDto.getUserPassword(), rtMember.getUserPassword(), 10)) {
//			if(true) {
				httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
				httpSession.setAttribute("sessSeqXdm", rtMember.getSeq());
				httpSession.setAttribute("sessIdXdm", rtMember.getUserId());
				httpSession.setAttribute("sessNameXdm", rtMember.getName());
	//			if(httpSession.getAttribute("prevPage") != null) {
	////				returnMap.put("rtp", "buy");
	//				System.out.println("주소: " + httpSession.getAttribute("prevPage"));
	//				
	//				
	//				System.out.println("구매성공");
	//			}else {
	////				returnMap.put("rtp", "fail");
	//				System.out.println("주소: " + httpSession.getAttribute("prevPage"));
	//				System.out.println("구매실패");
	//			}
				
				String prevPage = (String) httpSession.getAttribute("prevPage");
				System.out.println("주소테스트: "+prevPage);
				httpSession.removeAttribute("prevPage"); 
				returnMap.put("redirectUrl", prevPage != null ? prevPage : "/index");
				
				System.out.println("성공");
				
				returnMap.put("rt", "success");
			}else {
				returnMap.put("rt", "fail");
			}
			
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
		// 세션에서 액세스 토큰 가져오기
        String accessToken = (String) httpSession.getAttribute("accessToken");
        if (accessToken != null) {
            // 카카오 로그아웃 호출
            kakaoLoginService.logout(accessToken);
        }
		httpSession.invalidate();
		returnMap.put("rt", "success");
		return returnMap;
	}
}
