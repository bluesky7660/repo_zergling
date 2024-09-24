package com.exion.infra.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.common.util.UtilDateTime;


@Controller
public class CodeGroupController {
	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model) {
		
		vo.setDateStart(vo.getDateStart() == null || vo.getDateStart() == "" ? null : UtilDateTime.add00TimeString(vo.getDateStart()));
		vo.setDateEnd(vo.getDateEnd() == null || vo.getDateEnd() == "" ? null : UtilDateTime.add59TimeString(vo.getDateEnd()));
		vo.setParamsPaging(codeGroupService.listCount(vo));
		System.out.println("---------------------------------------------");
		System.out.println("번호thisPage: " + vo.getThisPage());
		System.out.println("번호StartPage: " + vo.getStartPage());
		System.out.println("번호EndPage: " + vo.getEndPage());
//		vo.setDateStart(vo.getDateStart()+" 00:00:00");
//		vo.setDateEnd(vo.getDateEnd()+" 00:00:00");
		System.out.println("S1:"+vo.getDateStart());
		System.out.println("S2:"+vo.getDateEnd());
		System.out.println("---------------------------------------------");
		if(vo.getTotalRows() > 0) {
			model.addAttribute("response", codeGroupService.selectList(vo));
		}
		return "/xdm/v1/infra/codegroup/codeGroupXdmList";
	}
//	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmSrch")
//	public String codeGroupXdmSrch(@RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "size", defaultValue = "3") int size,
//            @RequestParam(value = "searchKeyword", required = false) String searchKeyword, Model model,CodeGroupDto codeGroupDto) {
//		
//		PagingResponseDto<CodeGroupDto> responseDto = codeGroupService.findAll(page, size, searchKeyword);
//		model.addAttribute("response", responseDto);
//		
//		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
//	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmForm")
	public String codeGroupXdmForm() {
		return "/xdm/v1/infra/codegroup/codeGroupXdmForm";
	}
	 
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		System.out.println("codegroupDto.getSeq:"+codeGroupDto.getSeq());
//		return"";
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmMfom")
	public String codeGroupXdmMfom(Model model,CodeGroupDto codeGroupDto) {
//		codeGroupService.insert(codeGroupDto);
//		CodeGroupDto dto = codeGroupService.selectOne(codeGroupDto);
//		model.addAttribute("item", dto);
//		System.out.println("그룹이름: "+codeGroupService.selectOne(codeGroupDto).getGroupDesc());
//		System.out.println("그룹이름1: "+codeGroupService.selectOne(codeGroupDto).getGroupDesc());
		model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		return "xdm/v1/infra/codegroup/codeGroupXdmMfom";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		codeGroupService.update(codeGroupDto);
		System.out.println("update seq:"+codeGroupDto.getSeq());
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmUelt")
	public String codeGroupXdmUelt(CodeGroupDto codeGroupDto) {
		codeGroupService.uelete(codeGroupDto);
		System.out.println("uelete seq:"+codeGroupDto.getSeq());
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmDelt")
	public String codeGroupXdmDelt(CodeGroupDto codeGroupDto) {
		codeGroupService.delete(codeGroupDto);
		return "redirect:/v1/infra/codegroup/codeGroupXdmList";
	}
}
