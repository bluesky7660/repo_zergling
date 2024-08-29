package com.exion.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeController {
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/v1/infra/code/codeXdmList")
	public String codeXdmList(Model model) {
		List<CodeDto> commonCodes = codeService.selectList();
//		
//		for(CodeDto code:commonCodes) {
//			System.out.printf("|%-5s|%-12s|\n",code.getSeq(),code.getCodeName());
//		}
		model.addAttribute("list2", commonCodes);
		model.addAttribute("totalRows", commonCodes.size());
		return "/xdm/v1/infra/code/codeXdmList";
	}
	

}
