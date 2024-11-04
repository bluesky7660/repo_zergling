package com.exion.infra.huggingface;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HuggingFaceService {
	@Value("${huggingface.api.token}")
    private String apiToken;
	
	public String generateCover(String prompt) {
		RestTemplate restTemplate = new RestTemplate();
	    String url = "https://api-inference.huggingface.co/models/CompVis/stable-diffusion-v1-4";

	    // 요청 바디 생성
	    String requestBody = "{\"inputs\": \"" + prompt + "\"}";

	    // 헤더 설정
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer " + apiToken);

	    // HTTP 엔티티 생성
	    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

	    // POST 요청 보내기
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

	    // 응답 내용 출력
	    System.out.println("API 응답: " + response.getBody());

	    // 응답에서 이미지 URL 추출 (여기서는 예시로 응답 JSON에서 image_url을 찾는다고 가정)
	    String imageUrl = response.getBody();
	    return imageUrl;
	}

}
