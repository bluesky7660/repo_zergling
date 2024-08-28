package com.exion.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/v1/infra/code/codeXdmList")
	public String codeXdmList() {
		List<CodeDto> commonCodes = codeService.selectList();
		
		for(CodeDto code:commonCodes) {
			System.out.printf("|%-5s|%-12s|\n",code.getSeq(),code.getCodeName());
		}
		
		return "/xdm/v1/infra/code/codeXdmList";
	}
	

}
