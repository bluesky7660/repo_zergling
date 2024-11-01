package com.exion.infra.kakao.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoLoginService {
	
	@Value("${kakao.rest.api.key}")
    private String apiKey; // application.properties에 API 키 설정
	
	@Value("${kakao.login.redirectUri}")
    private String redirectUri; // application.properties에 API 키 설정
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private final String logoutUri = "https://kapi.kakao.com/v1/user/logout"; 
	private final String accessTokenUri = "https://kauth.kakao.com/oauth/token"; // 카카오 액세스 토큰 요청 URI
	
	public String getAuthorizationUrl() {
        return "https://kauth.kakao.com/oauth/authorize?client_id=" + apiKey +
               "&redirect_uri=" + redirectUri +
               "&response_type=code";
    }
	//인가 코드를 받아서 accessToken을 반환
//	public String getAccessToken(String code){
//		String accessToken = "";
//	    String refreshToken = "";
//	    String reqUrl = "https://kauth.kakao.com/oauth/token";
//	    try{
//	        URL url = new URL(reqUrl);
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        
//	        //필수 헤더 세팅
//	        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//	        conn.setDoOutput(true); //OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
//
//	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//	        StringBuilder sb = new StringBuilder();
//	        
//	        //필수 쿼리 파라미터 세팅
//	        sb.append("grant_type=authorization_code");
//	        sb.append("&client_id=").append(kakaoApiKey);
//	        sb.append("&redirect_uri=").append(kakaoRedirectUri);
//	        sb.append("&code=").append(code);
//
//	        bw.write(sb.toString());
//	        bw.flush();
//
//	        int responseCode = conn.getResponseCode();
//	        log.info("[KakaoApi.getAccessToken] responseCode = {}", responseCode);
//
//	        BufferedReader br;
//	        if (responseCode >= 200 && responseCode < 300) {
//	            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        } else {
//	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//	        }
//
//	        String line = "";
//	        StringBuilder responseSb = new StringBuilder();
//	        while((line = br.readLine()) != null){
//	            responseSb.append(line);
//	        }
//	        String result = responseSb.toString();
//	        log.info("responseBody = {}", result);
//
//	        JsonParser parser = new JsonParser();
//	        JsonElement element = parser.parse(result);
//	        accessToken = element.getAsJsonObject().get("access_token").getAsString();
//	        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
//
//	        br.close();
//	        bw.close();
//	    }catch (Exception e){
//	        e.printStackTrace();
//	    }
//	    return accessToken;
//
//    }

    //accessToken을 받아서 UserInfo 반환
//	public HashMap<String, Object> getUserInfo(String accessToken) {...}
//    //accessToken을 받아서 로그아웃 시키는 메서드
//	public void kakaoLogout(String accessToken) {...}
	public LoginResponseDto getAccessToken(String code){
		System.out.println("code:"+code);
		// 헤더 설정
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    // 요청 본문 설정
	    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
	    body.add("grant_type", "authorization_code");
	    body.add("client_id", apiKey);
	    body.add("redirect_uri", redirectUri);
	    body.add("code", code);

	    // HttpEntity 생성
	    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
	    // 액세스 토큰 요청
	    ResponseEntity<LoginResponseDto> responseEntity = restTemplate.exchange(accessTokenUri, 
	    		HttpMethod.POST, requestEntity, LoginResponseDto.class);
	    
	    return responseEntity.getBody();
	}
	
	// 사용자 정보 요청
    public Map<String, Object> getUserInfo(String accessToken) {
    	System.out.println("getUserInfo");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        // HttpEntity 생성 (헤더 포함)
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // REST API 요청
        ResponseEntity<Map> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, entity, Map.class);
        System.out.println("사용자정보:"+response.getBody());
        return response.getBody();
    }

    // Access Token 갱신
    public LoginResponseDto refreshToken(String refreshToken) {
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	System.out.println("refreshToken");
    	MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    	body.add("grant_type", "refresh_token");
    	body.add("client_id", apiKey);
    	body.add("refresh_token", refreshToken);

        

    	HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<LoginResponseDto> responseEntity = restTemplate.exchange(accessTokenUri, HttpMethod.POST, requestEntity, LoginResponseDto.class);

        return responseEntity.getBody();
    }
    public void logout(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(logoutUri, HttpMethod.POST, entity, String.class);

            // 로그아웃 요청에 대한 응답 확인
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Successfully logged out of Kakao.");
            } else {
                System.err.println("Failed to log out of Kakao: " + response.getBody());
            }
        } catch (RestClientException e) {
            // 로그아웃 요청 실패 시 처리
            System.err.println("Error occurred during Kakao logout: " + e.getMessage());
        }
    }
}
