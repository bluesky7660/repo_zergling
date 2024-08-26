package com.exion.infra.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {
	@Autowired
	CodeGroupService service;
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmList")
	public String codeGroupXdmList() {
		
		service.selectList();
		return "/xdm/v1/infra/codegroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmForm")
	public String codeGroupXdmForm() {
		return "/xdm/v1/infra/codegroup/codeGroupXdmForm";
	} 
}
