package com.exion.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exion.infra.codegroup.CodeGroupDto;
import com.exion.infra.codegroup.CodeGroupService;
import com.exion.infra.codegroup.PagingResponseDto;

@Controller
public class CodeController {
	@Autowired
	CodeService codeService;
	
	@Autowired
	CodeGroupService codeGroupService;
	 
//	@RequestMapping(value = "/v1/infra/code/codeXdmList")
//	public String codeXdmList(@RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "size", defaultValue = "3") int size,
//            @RequestParam(value = "searchKeyword", required = false) String searchKeyword, Model model) {
////		List<CodeDto> commonCodes = codeService.selectList();		
////		model.addAttribute("list2", commonCodes);
////		model.addAttribute("totalRows", commonCodes.size());
////		PagingResponseDto<CodeDto> responseDto = codeService.findAll(page, size, searchKeyword);
//		model.addAttribute("response", responseDto);
//		model.addAttribute("response", codeService.selectList());
//		return "/xdm/v1/infra/code/codeXdmList";
//	}
	@RequestMapping(value = "/v1/infra/code/codeXdmList")
	public String codeXdmList(CodeVo vo, Model model) {
//		System.out.println("1:"+ codeService.selectList(vo).getCurrentPage());
		System.out.println("---------------------------------------------");
		System.out.println("S:"+vo.getDateStart());
		System.out.println("E:"+vo.getDateEnd());
		model.addAttribute("response", codeService.selectList(vo));
//		model.addAttribute("count", codeService.listCount(vo));
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
	public String codeXdmMfom(Model model,CodeDto codeDto) {
		List<CodeGroupDto> codeGroups = codeGroupService.selectList();
		model.addAttribute("codeGroups", codeGroups);
		model.addAttribute("item",codeService.selectOne(codeDto));
		System.out.println("코드그룹seq: "+codeService.selectOne(codeDto).getCodeGroup_seq());
//		System.out.println("그룹정보: "+codeGroups);
		for(CodeGroupDto codeGroup:codeGroups) {
			System.out.println("그룹SEQ: "+codeGroup.getSeq());
//			System.out.printf("|%-5s|%-5s|\n",codeGroup.getSeq(),codeGroup.getCodeGroupName());
		} 
		return "xdm/v1/infra/code/codeXdmMfom";
	}
	
	@RequestMapping(value = "/v1/infra/code/codeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmUelt")
	public String codeXdmUelt(CodeDto codeDto) {
		codeService.uelete(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}
	@RequestMapping(value = "/v1/infra/code/codeXdmDelt")
	public String codeXdmDelt(CodeDto codeDto) {
		codeService.delete(codeDto);
		return "redirect:/v1/infra/code/codeXdmList";
	}
	//------------------------
//	@RequestMapping(value = "/v1/infra/codeGroup/codeGroupXdmList")
//	public String codeXdmForm() {
//
//		return "/xdm/v1/infra/codeGroup/codeGroupXdmList";
//	}
	

}
