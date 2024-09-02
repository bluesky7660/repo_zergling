package com.exion.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.codegroup.CodeGroupDto;
import com.exion.infra.codegroup.CodeGroupService;

@Controller
public class CodeController {
	@Autowired
	CodeService codeService;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/v1/infra/code/codeXdmList")
	public String codeXdmList(Model model) {
		List<CodeDto> commonCodes = codeService.selectList();
		
		
		model.addAttribute("list2", commonCodes);
		
		model.addAttribute("totalRows", commonCodes.size());
		return "/xdm/v1/infra/code/codeXdmList";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmForm")
	public String codeXdmForm(Model model) {
		List<CodeGroupDto> codeGroups = codeGroupService.selectList();
		model.addAttribute("codeGroups", codeGroups);
		return "/xdm/v1/infra/code/codeXdmForm";
	}
	
	@RequestMapping(value = "/v1/infra/code/codeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/code/codeXdmMfom")
	public String codeXdmMfom(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}
	//------------------------
//	@RequestMapping(value = "/v1/infra/codeGroup/codeGroupXdmList")
//	public String codeXdmForm() {
//
//		return "/xdm/v1/infra/codeGroup/codeGroupXdmList";
//	}
	

}
