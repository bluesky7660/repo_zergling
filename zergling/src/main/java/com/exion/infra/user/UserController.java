package com.exion.infra.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/v1/infra/user/userXdmList")
	public String userXdmList(Model model,@ModelAttribute("vo") UserVo vo) {
		vo.setParamsPaging(userService.listCount(vo));
		model.addAttribute("list3", userService.selectXdmUser(vo)) ;
		System.out.println("1: "+vo.getDateOfBirth());
		return "xdm/v1/infra/user/userXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/user/userXdmForm")
	public String userXdmForm() {

		return "xdm/v1/infra/user/userXdmForm";
	}
	@RequestMapping(value = "/v1/infra/user/userXdmInst")
	public String userXdmForm(UserDto userDto) {
//		System.out.println("그룹이름: "+userDto.getDateOfBirth());	
		userService.insert(userDto);
		return "redirect:/v1/infra/user/userXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/user/userXdmMfom")
	public String userXdmMFom(UserDto userDto,Model model) {
		model.addAttribute("item", userService.selectOne(userDto));
		return "xdm/v1/infra/user/userXdmMfom";
	}
	@RequestMapping(value = "/v1/infra/user/userXdmUpdt")
	public String userXdmUpdt(UserDto userDto) {
		userService.update(userDto);
		return "redirect:/v1/infra/user/userXdmList";
	}
}
