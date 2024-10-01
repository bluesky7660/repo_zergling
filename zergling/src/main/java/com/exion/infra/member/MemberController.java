package com.exion.infra.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.infra.util.Constants;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/v1/infra/member/memberXdmList")
	public String memberXdmList(Model model,@ModelAttribute("vo") MemberVo vo) {
		vo.setParamsPaging(memberService.listCount(vo));
		model.addAttribute("list3", memberService.selectXdmMember(vo)) ;
		System.out.println("1: "+vo.getDateOfBirth());
		return "/xdm/v1/infra/member/memberXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/member/memberXdmForm")
	public String memberXdmForm() {

		return "/xdm/v1/infra/member/memberXdmForm";
	}
	@RequestMapping(value = "/v1/infra/member/memberXdmInst")
	public String memberXdmForm(MemberDto memberDto) {
//		System.out.println("그룹이름: "+memberDto.getDateOfBirth());	
		memberService.insert(memberDto);
		return "redirect:/v1/infra/member/memberXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/member/memberXdmMfom")
	public String memberXdmMFom(MemberDto memberDto,Model model) {
		model.addAttribute("item", memberService.selectOne(memberDto));
		return "xdm/v1/infra/member/memberXdmMfom";
	}
	@RequestMapping(value = "/v1/infra/member/memberXdmUpdt")
	public String memberXdmUpdt(MemberDto memberDto) {
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
	
	@ResponseBody
	@RequestMapping(value = "v1/infra/member/loginXdmProc")
	public Map<String, Object> loginXdmProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<>();
		MemberDto rtMember = memberService.selectXdmOne(memberDto);
		
		if (rtMember != null) {
//			MemberDto rtMember2 = service.selectOneLogin(dto);
			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqXdm", rtMember.getSeq());
			httpSession.setAttribute("sessIdXdm", rtMember.getUserId());
			httpSession.setAttribute("sessNameXdm", rtMember.getName());
			System.out.println("성공");
			returnMap.put("rt", "success");

		} else {
			System.out.println("실패");
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	@ResponseBody
	@RequestMapping(value = "v1/infra/member/loginUsrProc")
	public Map<String, Object> loginUsrProc(MemberDto memberDto, HttpSession httpSession) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<>();
		MemberDto rtMember = memberService.selectUsrOne(memberDto);
		System.out.println("rtMember: " + rtMember);
		
		if (rtMember != null) {
			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqXdm", rtMember.getSeq());
			httpSession.setAttribute("sessIdXdm", rtMember.getUserId());
			httpSession.setAttribute("sessNameXdm", rtMember.getName());
			System.out.println("성공");
			returnMap.put("rt", "success");

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
	
	@ResponseBody
	@RequestMapping(value = "/v1/infra/member/logoutUsrProc")
	public Map<String, Object> logoutUsrProc(HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		httpSession.invalidate();
		returnMap.put("rt", "success");
		return returnMap;
	}
}
