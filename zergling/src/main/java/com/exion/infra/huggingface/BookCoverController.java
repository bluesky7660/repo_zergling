package com.exion.infra.huggingface;

import java.util.HashMap;
import java.util.Map;

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
	
	@RequestMapping("/api/generate-cover")
	@ResponseBody
    public Map<String, String> generateCover(@RequestParam("prompt") String prompt) {
		String imageUrl = huggingFaceService.generateCover(prompt); // 프롬프트로 이미지 생성
        Map<String, String> response = new HashMap<>();
        response.put("image_url", imageUrl);
        return response;
    }
	@RequestMapping("/bookCover")
    public String bookCover() {
		return "usr/v1/test/bookCover";
    }
}
