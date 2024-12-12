package com.exion.infra.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exion.infra.util.Constants;


@RestController
@RequestMapping(value="/rest/member")
public class MemberRestController {
	@Autowired
	MemberService memberService;
	
	public String encodeBcrypt(String planeText, int strength) {
		  return new BCryptPasswordEncoder(strength).encode(planeText);
	}
			
	public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
	  return passwordEncoder.matches(planeText, hashValue);
	}
	
//	@RequestMapping(value = "", method = RequestMethod.GET)
	@GetMapping("")
	public List<MemberDto> selectList(MemberVo vo) throws Exception {
		List<MemberDto> list = memberService.selectMember(vo);
		System.out.println(list+"@@@@@@@@@@@@@");
		return list;
	}
	@GetMapping("/{seq}")
	public MemberDto selectOne(MemberDto memberDto) throws Exception {
		MemberDto item = memberService.selectOne(memberDto);
		return item;
	}
	
//	@PostMapping("/login")
//	public Map<String, Object> loginUsrProc(MemberDto memberDto) throws Exception {
//		
//		MemberDto rtMember = memberService.selectUsrOne(memberDto);
//		System.out.println("rtMember: " + rtMember);
//		if (rtMember != null&& matchesBcrypt(memberDto.getUserPassword(), rtMember.getUserPassword(), 10)) {
//			
//	
//				
//				String prevPage = (String) httpSession.getAttribute("prevPage");
//				System.out.println("주소테스트: "+prevPage);
//				httpSession.removeAttribute("prevPage"); 
//				returnMap.put("redirectUrl", prevPage != null ? prevPage : "/index");
//				
//				System.out.println("성공");
//				
//				returnMap.put("rt", "success");
//			}else {
//				returnMap.put("rt", "fail");
//			}
//			
//		} else {
//			System.out.println("실패");
//			returnMap.put("rt", "fail");
//		}
//		return returnMap;
//	}
	
}
