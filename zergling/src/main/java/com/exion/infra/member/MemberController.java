package com.exion.infra.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
