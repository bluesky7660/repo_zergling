package com.exion.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {
	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmList")
	public String codeGroupXdmList() {
		
		List<CodeGroupDto> codeGroups = codeGroupService.selectList();
		System.out.println("----------------------------------------------------");
		for(CodeGroupDto codeGroupDto : codeGroups) {
//			System.out.println(codeGroupDto.getSeq() + "|" + codeGroupDto.getCodeGroupName() + "|" + codeGroupDto.getGroupOrder() + "|" + codeGroupDto.getGroupDesc()+ "|" + codeGroupDto.getUseNy()+ "|" + codeGroupDto.getDelNy()+ "|" + a.getRegDate()+ "|" + a.getModDate());
			System.out.printf("| %-5s | %-9s | %-3d | %-15s |%n", codeGroupDto.getSeq(), codeGroupDto.getCodeGroupName(), codeGroupDto.getGroupOrder(), codeGroupDto.getGroupDesc());
		}
		System.out.println("----------------------------------------------------");
		System.out.println("codeGroups: "+codeGroups.size());
		return "/xdm/v1/infra/codegroup/codeGroupXdmList"; 
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmForm")
	public String codeGroupXdmForm() {
		return "/xdm/v1/infra/codegroup/codeGroupXdmForm";
	} 
}
