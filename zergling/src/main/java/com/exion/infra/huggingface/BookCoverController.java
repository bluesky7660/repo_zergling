package com.exion.infra.huggingface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookCoverController {
	@Autowired
	HuggingFaceService huggingFaceService;
	
	@PostMapping("/api/generate-cover")
	@ResponseBody
    public String generateCover(@RequestParam("prompt") String prompt) {
        return huggingFaceService.generateCover(prompt);
    }
	@RequestMapping("/bookCover")
    public String bookCover() {
		return "usr/v1/test/bookCover";
    }
}
