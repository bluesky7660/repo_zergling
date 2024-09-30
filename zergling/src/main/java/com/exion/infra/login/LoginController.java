package com.exion.infra.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.infra.member.MemberDto;
import com.exion.infra.member.MemberService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@Autowired
	MemberService memberService;

	
	@RequestMapping(value = "v1/infra/loginXdm1")
	public String loginXdm1() {
		return "xdm/v1/infra/member/memberXdmLogin";
	}
	@RequestMapping(value = "v1/infra/index/indexXdmView")
	public String indexXdmView() {
		return "xdm/v1/infra/index/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "v1/infra/member/loginProc1")
	public Map<String, Object> loginProc1(MemberDto memberDto) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<>();
		MemberDto rtMember = loginService.selectXdmOne(memberDto);
		
		if (rtMember != null) {
			System.out.println("성공");
			returnMap.put("rt", "success");
//			dto.setIfmmPassword(UtilSecurity.encryptSha256(dto.getIfmmPassword()));
//			MemberDto rtMember2 = service.selectOneLogin(dto);
//
//			if (rtMember2 != null) {
//				
//				if(dto.getAutoLogin() == true) {
//					UtilCookie.createCookie(
//							Constants.COOKIE_SEQ_NAME_XDM, 
//							rtMember2.getIfmmSeq(), 
//							Constants.COOKIE_DOMAIN_XDM, 
//							Constants.COOKIE_PATH_XDM, 
//							Constants.COOKIE_MAXAGE_XDM);
//				} else {
//					// by pass
//				}
//
//				httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
//				httpSession.setAttribute("sessSeqXdm", rtMember2.getIfmmSeq());
//				httpSession.setAttribute("sessIdXdm", rtMember2.getIfmmId());
//				httpSession.setAttribute("sessNameXdm", rtMember2.getIfmmName());
//
//				rtMember2.setIfmmSocialLoginCd(103);
//				rtMember2.setIflgResultNy(1);
//				service.insertLogLogin(rtMember2);
//
//				returnMap.put("rt", "success");
//			} else {
//				dto.setIfmmSocialLoginCd(103);
//				dto.setIfmmSeq(rtMember.getIfmmSeq());
//				dto.setIflgResultNy(0);
//				service.insertLogLogin(dto);
//
//				returnMap.put("rt", "fail");
//			}
		} else {
//			dto.setIfmmSocialLoginCd(103);
//			dto.setIflgResultNy(0);
//			service.insertLogLogin(dto);
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	
}
