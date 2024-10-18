//package com.exion.infra.recaptcha;
//
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import org.springframework.stereotype.Service;
//
//import com.wf.captcha.base.Captcha;
//
//import jakarta.servlet.http.HttpSession;
//
//@Service
//public class CaptchaUtil {
//
//	// CAPTCHA 생성 메서드
//    public static byte[] generateCaptcha(HttpSession session) throws IOException {
//        Captcha captcha = new Captcha();
//        // CAPTCHA 정답을 세션에 저장
//        session.setAttribute("captcha", captcha.getAnswer());
//
//        // CAPTCHA 이미지를 byte 배열로 반환
//        BufferedImage captchaImage = captcha.getImage();
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        ImageIO.write(captchaImage, "png", os);
//        return os.toByteArray();
//    }
//
//    // CAPTCHA 검증 메서드
//    public static boolean validateCaptcha(String userInput, HttpSession session) {
//        String captchaAnswer = (String) session.getAttribute("captcha");
//        return captchaAnswer != null && captchaAnswer.equals(userInput);
//    }
//}
