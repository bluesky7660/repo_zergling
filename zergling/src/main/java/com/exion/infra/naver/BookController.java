package com.exion.infra.naver;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BookController {
	@Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    // 네이버 Books API를 호출할 엔드포인트 설정
    private final String NAVER_API_URL = "https://openapi.naver.com/v1/search/book.json";
    
	@GetMapping("/searchBook")
	public List<BookVo> getBestSellers(@RequestParam(value = "query", defaultValue = "베스트셀러") String query) {   
		try {
			 RestTemplate restTemplate = new RestTemplate();
	        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	        headers.set("X-Naver-Client-Id", clientId);
	        headers.set("X-Naver-Client-Secret", clientSecret);
	        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);

	        String url = NAVER_API_URL + "?query=" + query + "&display=20&start=1";
	        org.springframework.http.ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);

	        // JSON 파싱
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<BookVo> books = new ArrayList<>();
	        NaverResultVo resultVo = objectMapper.readValue(response.getBody(), NaverResultVo.class);
	        books = resultVo.getItems();

	        return books; 
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }
//		URI uri = UriComponentsBuilder
//		        .fromUriString("https://openapi.naver.com")
//		        .path("/v1/search/book.json")
//		        .queryParam("query", text)
//		        .queryParam("display", 10)
//		        .queryParam("start", 1)
//		        .queryParam("sort", "sim")
//		        .encode()
//		        .build()
//		        .toUri();
//		
//		RequestEntity<Void> req = RequestEntity
//				 .get(uri)
//				 .header("X-Naver-Client-Id", clientId)
//				 .header("X-Naver-Client-Secret", clientSecret)
//				 .build();
//		
//		// Spring 제공 restTemplate
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
//        
//     // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
//        ObjectMapper om = new ObjectMapper();
//        NaverResultVo resultVo = null;
//        
//        try {
//        	resultVo = om.readValue(resp.getBody(), NaverResultVo.class);
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//        
//        List<BookVo> books =resultVo.getItems();	// books를 list.html에 출력 -> model 선언
//        model.addAttribute("books", books);
//
//		return "/book/list";
	
	}
}
