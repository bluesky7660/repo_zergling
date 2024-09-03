package com.exion.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.code.CodeService;


@Controller
public class CodeGroupController {
	@Autowired
	CodeGroupService codeGroupService;

	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmList")
	public String codeGroupXdmList(Model model) {
//		List<CodeGroupDto> codeGroups = codeGroupService.selectList();
//		
//		model.addAttribute("list", codeGroups);
//		for(CodeGroupDto codeGroup:codeGroups) {
//			System.out.println("날짜: "+codeGroup.getRegDate());
//			System.out.printf("|%-5s|%-5s|\n",codeGroup.getSeq(),codeGroup.getCodeGroupName());
//		}
		model.addAttribute("list", codeGroupService.selectList());
		model.addAttribute("totalRows", codeGroupService.selectList().size());
		
		return "/xdm/v1/infra/codegroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmForm")
	public String codeGroupXdmForm() {
		return "/xdm/v1/infra/codegroup/codeGroupXdmForm";
	}
	 
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		System.out.println("그룹이름: "+codeGroupDto.getCodeGroupName());	
		
		codeGroupService.insert(codeGroupDto);
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmMfom")
	public String codeGroupXdmMfom(Model model,CodeGroupDto codeGroupDto) {
//		codeGroupService.insert(codeGroupDto);
//		CodeGroupDto dto = codeGroupService.selectOne(codeGroupDto);
//		model.addAttribute("item", dto);
		model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		System.out.println("그룹이름: "+codeGroupService.selectOne(codeGroupDto).getGroupDesc());
		return "xdm/v1/infra/codegroup/codeGroupXdmMfom";
	}
}
