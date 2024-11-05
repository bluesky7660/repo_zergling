package com.exion.infra.huggingface;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class HuggingFaceService {
	@Value("${huggingface.api.token}")
    private String apiToken;
	
	public String generateCover(String prompt) {
		try {
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
		    ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
	
		    // 응답을 Blob처럼 처리
	        byte[] imageBytes = response.getBody();
	        if (imageBytes != null) {
	            // Base64 인코딩
	            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	            // 데이터 URL 형식 생성
	            return "data:image/png;base64," + base64Image;
	        } else {
	            throw new RuntimeException("이미지를 생성할 수 없습니다.");
	        }
		}
        catch (HttpServerErrorException e) {
            // 서비스 불가 오류 처리
            String responseBody = e.getResponseBodyAsString();
            String estimatedTimeMessage = "서비스가 사용 불가능합니다.";
            if (responseBody.contains("estimated_time")) {
                // 예상 시간 파싱
                String estimatedTime = responseBody.replaceAll(".*\"estimated_time\":(\\d+\\.\\d+).*", "$1");
                // 소수점 제외
                int estimatedSeconds = (int) Math.floor(Double.parseDouble(estimatedTime));
                estimatedTimeMessage = "모델이 로딩 중입니다. 예상 시간: " + estimatedSeconds + "초 후에 재시도해주세요.";
            }
            return estimatedTimeMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return "이미지 생성에 실패했습니다.";   
		}
	}

}
