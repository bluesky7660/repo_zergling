package com.exion.infra.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/v1/infra/user/userXdm")
	public String userXdm() {
		List<UserDto> users = userService.selectUser();
		for(UserDto user:users ) {
			String admNy = user.getAdmNy() == 1 ? "관리자" : "유저";
			System.out.printf("|%-5s|%-5s|%-5s|\n",user.getSeq(),admNy,user.getName());
		}
		return "xdm/v1/infra/user/userXdm";
	}
}
