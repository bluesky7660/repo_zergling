package com.exion.infra.recaptcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class RecaptchaService {
	@Value("${google.recaptcha.secret.key}")
	private String secretKey;
	
	@Value("${google.recaptcha.api.key}")
    private String apiKey; // API 키 추가
	
	@Value("${google.recaptcha.projectID}")
	private String projectId; // 프로젝트 ID 추가
	
	@Value("${google.recaptcha.site.key}") 
    private String siteKey;	// site_key 추가
	
//	private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	private static final String RECAPTCHA_VERIFY_URL = "https://recaptchaenterprise.googleapis.com/v1/projects/";
	public boolean verifyRecaptcha(String token,String expectedAction) {
		RestTemplate restTemplate = new RestTemplate();
        String url = RECAPTCHA_VERIFY_URL + projectId + "/assessments?key=" + apiKey; // API 키 포함
        System.out.println("url:"+url);
        // JSON 요청 본문 생성
     // JSON 요청 본문 생성 - expectedAction과 site_key를 추가합니다.
        String jsonRequest = String.format(
            "{\"event\": {\"token\": \"%s\", \"expectedAction\": \"%s\", \"siteKey\": \"%s\"}}", 
            token, 
            expectedAction, 
            siteKey
        );
        
        // reCAPTCHA Enterprise API 요청
        String response = restTemplate.postForObject(url, jsonRequest, String.class);
        
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        boolean success = jsonResponse.has("tokenProperties") && jsonResponse.getAsJsonObject("tokenProperties").get("valid").getAsBoolean();
        
        // reCAPTCHA 응답 로깅
        System.out.println("reCAPTCHA 검증 응답: " + jsonResponse.toString());

        if (!success) {
            JsonArray errorCodes = jsonResponse.getAsJsonArray("error-codes");
            System.out.println("reCAPTCHA 검증 실패 코드: " + errorCodes);
        }
        return success;
	}
}
