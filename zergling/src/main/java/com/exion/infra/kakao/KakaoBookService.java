package com.exion.infra.kakao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoBookService {
	
	@Value("${kakao.rest.api.key}")
    private String apiKey; // application.properties에 API 키 설정
	
	public List<KakaoBookVo> getBestSellers(String query) {
        String url = "https://dapi.kakao.com/v3/search/book?query="+query; // 베스트셀러 검색 API URL

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoBookResponse> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                KakaoBookResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getDocuments(); // 베스트셀러 목록 반환
        }
        
        return List.of(); // 빈 리스트 반환
    }
	
}
