package com.exion.mall.product;

import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exion.infra.code.CodeService;



@Controller
public class AuthorController {
	@Autowired
	AuthorService authorService;
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "v1/mall/product/authorXdmList")
	public String authorXdmList(Model model,@ModelAttribute("vo") AuthorVo vo){
		vo.setParamsPaging(authorService.listCount(vo));
		model.addAttribute("list", authorService.authorXdmList(vo));
		model.addAttribute("groups", codeService.authorTypeList());
		return "xdm/v1/mall/product/authorXdm";
	}
	@RequestMapping(value = "v1/mall/product/authorXdmForm")
	public String authorXdmForm(Model model) {
//		List<CodeDto> codes = codeService.selectList();
//		List<CodeDto> jobs = codes.stream().filter(code -> code.getCodeGroup_seq() == "10").collect(Collectors.toList());
		List<AuthorDto> jobs = authorService.jobList();
//		for(AuthorDto job: jobs) {
//			System.out.println("이름: "+job.getCodeName());
//		}
		
		model.addAttribute("jobs",authorService.jobList());
		return "xdm/v1/mall/product/authorXdmForm";
	}
	
	@RequestMapping(value = "v1/mall/product/authorXdmMfom")
	public String authorXdmMfom(AuthorDto authorDto, Model model) {
		System.out.println("1: "+authorService.authorOne(authorDto));
		model.addAttribute("item", authorService.authorOne(authorDto));
		model.addAttribute("jobs",authorService.jobList());
		return "xdm/v1/mall/product/authorXdmMfom";
	}
	@RequestMapping(value = "v1/mall/product/authorXdmInst")
	public String authorXdmInst(AuthorDto authorDto) {
		try {
			authorService.insertAuthor(authorDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:authorXdmList";
	}
	@RequestMapping(value = "v1/mall/product/authorXdmUpdt")
	public String authorXdmUpdt(AuthorDto authorDto) {
		try {
			authorService.update(authorDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:authorXdmList";
	}
	@RequestMapping(value = "v1/mall/product/authorXdmUelt")
	public String authorXdmUelt(AuthorDto authorDto) {
		authorService.uelete(authorDto);
		return "redirect:authorXdmList";
	}
	@RequestMapping(value = "v1/mall/product/authorXdmDelt")
	public String authorXdmDelt(AuthorDto authorDto) {
		authorService.delete(authorDto);
		return "redirect:authorXdmList";
	}
}
