package com.exion.infra.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/v1/infra/user/userXdm")
	public String userXdm(Model model) {
		model.addAttribute("list3", userService.selectUser()) ;
		model.addAttribute("totalRows", userService.selectUser().size());
		return "xdm/v1/infra/user/userXdm";
	}
}
