package com.exion.infra.kakao.login;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exion.infra.util.Constants;

import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoLoginController {
	
	@Autowired
	KakaoLoginService kakaoLoginService;
	
	//@RequestMapping("/kakao")
//    public String kakao(@RequestParam("code") String code){
//		// 1. 인가 코드 받기 (@RequestParam String code)
//
//        // 2. 토큰 받기
//        String accessToken = kakaoLoginService.getAccessToken(code);
//
//        // 3. 사용자 정보 받기
//        Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
//        String email = (String)userInfo.get("email");
//        String nickname = (String)userInfo.get("nickname");
//
//        System.out.println("email = " + email);
//        System.out.println("nickname = " + nickname);
//        System.out.println("accessToken = " + accessToken);
//        return "redirect:/";
//    }
	@GetMapping("/kakaoLogin")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
	    // 액세스 토큰 요청 및 사용자 정보 요청 로직
	    LoginResponseDto loginResponse = kakaoLoginService.getAccessToken(code);
	    System.out.println("loginResponse");
	    Map<String, Object> userInfo = kakaoLoginService.getUserInfo(loginResponse.getAccess_token());

	    // 사용자 정보를 세션에 저장
//	    session.setAttribute("sessIdXdm", userInfo.get("id"));
//	    session.setAttribute("sessNameXdm", userInfo.get("nickname")); // 필요한 추가 정보 저장
	    
	    // properties 처리
	    Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");
	    String nickname = (String) properties.get("nickname");
	    
	    //kakao_account
	    Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
	    String email = (String) kakaoAccount.get("email"); // 이메일 가져오기
	    
	    if (nickname == null) {
//	        // 랜덤 숫자 생성 (0000~9999)
//	        Random random = new Random();
//	        int randomNumber = random.nextInt(10000); // 0부터 9999까지의 숫자 생성
//	        String formattedNumber = String.format("%04d", randomNumber); // 4자리로 포맷
//
//	        session.setAttribute("sessNameXdm", "카카오" + formattedNumber + "회원님");
	    	session.setAttribute("sessNameXdm", "카카오" + userInfo.get("id") + "회원님");
	    } else {
	        session.setAttribute("sessNameXdm", nickname);
	    }
	    session.setAttribute("sessIdXdm", email);
	    session.setAttribute("sessEmailXdm",email);
	    
	    session.setAttribute("kakoLogin", Constants.KAKAO_LOGIN);

	    return "redirect:/index"; // 로그인 후 이동할 페이지
	}
	@GetMapping("/kakaoAuth")
    public String kakaoAuth() {
		String authorizationUrl = kakaoLoginService.getAuthorizationUrl();
        return "redirect:" + authorizationUrl;
    }

}
