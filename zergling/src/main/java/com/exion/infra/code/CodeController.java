package com.exion.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.codegroup.CodeGroupDto;

@Controller
public class CodeController {
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/v1/infra/code/codeXdmList")
	public String codeXdmList() {
		List<CodeDto> commoncode = codeService.selectList();
		System.out.println("----------------------------------------------------");
		for(CodeDto codeDto : commoncode) {
//			System.out.println(codeDto.getSeq() + "|" + codeDto.getCodeName() + "|"+ codeDto.getCodeOrder() + "|"+ codeDto.getCodeDesc() + "|"+ codeDto.getUseNy() + "|"+ codeDto.getDelNy() + "|"+ codeDto.getRegDate() + "|"+ codeDto.getModDate() + "|");
			System.out.printf("| %-5s | %10s | %-3d | %-25s | %-5s |%n", codeDto.getSeq(), codeDto.getCodeName(), codeDto.getCodeOrder(), codeDto.getCodeDesc(),codeDto.getCodeGroup_seq());
//			System.out.printf("| %10s |%n", codeDto.getCodeName());
		}
		System.out.println("----------------------------------------------------");
		
		return "/xdm/v1/infra/code/codeXdmList";
	}
	
}
