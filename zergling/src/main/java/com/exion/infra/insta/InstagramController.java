package com.exion.infra.insta;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class InstagramController {
//	@Value("${instagram.client-id}")
//    private String clientId;
//
//    @Value("${instagram.client-secret}")
//    private String clientSecret;
//
//    @Value("${instagram.access-token}") // 환경변수나 properties 파일에서 읽어옵니다.
//    private String shortLivedToken;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    // 사용자 프로필 및 미디어 정보 요청
//    @GetMapping("/profile")
//    @ResponseBody
//    public Map<String, Object> profilePage() {
//        return profile(shortLivedToken);
//    }
//
//    public Map<String, Object> profile(String accessToken) {
//        String userUrl = "https://graph.instagram.com/me?fields=id,username,account_type,media_count&access_token=" + accessToken;
//        String mediaUrl = "https://graph.instagram.com/me/media?fields=id,caption,media_url,media_type&access_token=" + accessToken;
//
//        String userResponse = restTemplate.getForObject(userUrl, String.class);
//        String mediaResponse = restTemplate.getForObject(mediaUrl, String.class);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("user", userResponse);
//        responseMap.put("media", mediaResponse);
//        System.out.println("확인:" + responseMap);
//        return responseMap;
//    }
//
//    // 단기 Access Token을 장기 Access Token으로 변환
//    @GetMapping("/exchange-token")
//    @ResponseBody
//    public Map<String, Object> exchangeToken(@RequestParam("shortLivedToken") String shortLivedToken) {
//        String tokenUrl = "https://graph.instagram.com/access_token";
//
//        // 요청 본문 작성
//        String body = "grant_type=ig_exchange_token" +
//                "&client_id=" + clientId +
//                "&client_secret=" + clientSecret +
//                "&access_token=" + shortLivedToken;
//
//        // 요청 헤더 설정
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<String> entity = new HttpEntity<>(body, headers);
//
//        // Access Token 요청
//        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(tokenUrl, entity, Map.class);
//        Map<String, Object> responseBody = responseEntity.getBody();
//
//        return responseBody;
//    }
//	@Value("${instagram.access-token}") // 환경변수나 properties 파일에서 읽어옵니다.
//    private String accessToken;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
////    @GetMapping("/profile")
////    public ModelAndView profilePage() {
////        // 사용자 정보 요청
////        Map<String, Object> userProfile = profile(accessToken);
////
////        ModelAndView modelAndView = new ModelAndView("profile");
////        modelAndView.addObject("user", userProfile.get("user"));
////        modelAndView.addObject("media", userProfile.get("media"));
////
////        return modelAndView;
////    }
//    @GetMapping("/profile")
//    @ResponseBody
//    public Map<String, Object> profilePage() {
//        // 사용자 정보 요청
//        return profile(accessToken);
//    }
//
//    public Map<String, Object> profile(String accessToken) {
//        String userUrl = "https://graph.instagram.com/me?fields=id,username,account_type,media_count&access_token=" + accessToken;
//        String mediaUrl = "https://graph.instagram.com/me/media?fields=id,caption,media_url,media_type&access_token=" + accessToken;
//
//        String userResponse = restTemplate.getForObject(userUrl, String.class);
//        String mediaResponse = restTemplate.getForObject(mediaUrl, String.class);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("user", userResponse);
//        responseMap.put("media", mediaResponse);
//        System.out.println("확인:"+responseMap);
//        return responseMap;
//    }

}
