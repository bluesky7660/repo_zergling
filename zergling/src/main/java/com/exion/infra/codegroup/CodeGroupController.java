package com.exion.infra.codegroup;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CodeGroupController {
	@Autowired
	CodeGroupService codeGroupService;

	
	@RequestMapping(value = "/v1/infra/codegroup/codeGroupXdmList")
	public String codeGroupXdmList(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword, 
            @RequestParam(value = "sDelNy", required = false) Integer sDelNy,
            @RequestParam(value = "sUseNy", required = false) Integer sUseNy,
            @RequestParam(value = "dateType", required = false) Integer dateType,
            @RequestParam(value = "dateStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
            @RequestParam(value = "dateEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd,
            @RequestParam(value = "keywordType", required = false) Integer keywordType,
            Model model) {
//		List<CodeGroupDto> codeGroups = codeGroupService.selectList();
//		
//		model.addAttribute("list", codeGroups);
//		for(CodeGroupDto codeGroup:codeGroups) {
//			System.out.println("날짜: "+codeGroup.getRegDate());
//			System.out.printf("|%-5s|%-5s|\n",codeGroup.getSeq(),codeGroup.getCodeGroupName());
//		}       
//		List<CodeGroupDto> codeGroups = codeGroupService.findAll(page, size);
//		int total = codeGroupService.listCount(searchKeyword);
//		int totalPages = (int) Math.ceil((double) total / size);
		
//		model.addAttribute("list", codeGroupService.selectList());
//		model.addAttribute("list", codeGroupService.findAll(page, size, searchKeyword));
//		model.addAttribute("totalRows", total);
//		model.addAttribute("total", total);
//		model.addAttribute("pageSize", size);
//		model.addAttribute("currentPage", page);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("searchKeyword", searchKeyword);
		
//		PagingResponseDto responseDto = new PagingResponseDto(
//	            codeGroupService.findAll(page, size, searchKeyword),
//	            codeGroupService.listCount(searchKeyword),
//	            (int) Math.ceil((double) codeGroupService.listCount(searchKeyword) / size),
//	            page,
//	            size,
//	            searchKeyword
//	    );
        PagingResponseDto<CodeGroupDto> responseDto = codeGroupService.findAll(page, size, dateType, dateStart, dateEnd,
        		keywordType, sDelNy, sUseNy, searchKeyword);
        
		
		 model.addAttribute("response", responseDto);
		 
		 
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
