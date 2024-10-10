package com.exion.infra.kakao;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.common.util.SessionUtils;

@Controller
//@RequestMapping("/order/*")
public class KakaoPayController {
	
	private final KakaoPayService kakaoPayService;

    @Autowired
    public KakaoPayController(KakaoPayService kakaoPayService) {
        this.kakaoPayService = kakaoPayService;
    }

	@PostMapping("/order/pay/ready")
	@ResponseBody
    public ReadyResponse payReady(@RequestBody OrderCreateForm orderCreateForm) {
        
        String name = orderCreateForm.getName();
        int totalPrice = orderCreateForm.getTotalPrice();
        int quantity = orderCreateForm.getQuantity();
        String userId = orderCreateForm.getUserId();
        String deliveryReq = orderCreateForm.getUoDeliveryReq();
        
        System.out.println("주문 상품 이름: " + name);
        System.out.println("주문 금액: " + totalPrice);
        System.out.println("결제 회원 아이디: " + userId);
        // 카카오 결제 준비하기
        System.out.println("결제 고유번호2: " + kakaoPayService.payReady(name, totalPrice, quantity , userId).getTid());
        ReadyResponse readyResponse = kakaoPayService.payReady(name, totalPrice , quantity , userId);
        // 세션에 결제 고유번호(tid) 저장
        System.out.println("결제 고유번호: " + readyResponse.getTid());
        System.out.println("결제 회원 아이디: " + userId);
        SessionUtils.addAttribute("tid", readyResponse.getTid());
        SessionUtils.addAttribute("userId", userId);
        SessionUtils.addAttribute("Price", totalPrice);
        SessionUtils.addAttribute("quantity", quantity);
        SessionUtils.addAttribute("deliveryReq", deliveryReq);

        return readyResponse;
    }

	@GetMapping("/order/pay/completed")
    public String payCompleted(Model model, @RequestParam("pg_token") String pgToken) {
    
        String tid = SessionUtils.getStringAttributeValue("tid");
        String userId = SessionUtils.getStringAttributeValue("userId");
	        
        System.out.println("결제승인 요청을 인증하는 토큰: " + pgToken);
        System.out.println("결제 고유번호: " + tid);
        System.out.println("결제 회원 아이디: " + userId);

        // 카카오 결제 요청하기
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken,userId);
        System.out.println("결제승인:" +approveResponse.getPartner_order_id());
        System.out.println("completed: 결제가 성공적으로 완료되었습니다.");
        model.addAttribute("pay", approveResponse);
        return "usr/v1/pages/paySuccess";	
    }
//	private final KakaoPayService kakaoPayService;
//
//    @Autowired
//    public KakaoPayController(KakaoPayService kakaoPayService) {
//        this.kakaoPayService = kakaoPayService;
//    }

//    @ResponseBody
//    @PostMapping("/payment/request")
//    public ResponseEntity<?> initiatePayment() {
//        try {
//            Map<String, Object> response = kakaoPayService.initiatePayment();
//            System.out.println("성공~~~~!");
//            String result = "결제가 준비되었습니다. TID: " + response.get("tid");
//            return ResponseEntity.ok(result); // JSON 형태로 응답
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("결제 준비 중 오류 발생: " + e.getMessage());
//        }
//    }
//    @PostMapping("/request")
//    @ResponseBody
//    public Map<String, Object> requestPayment() {
//    	Map<String, Object> paymentResponse = new HashMap<>();
//        try {
//            // 결제 준비 로직
//            Map<String, Object> response = kakaoPayService.initiatePayment();
//            
//            // 모든 데이터를 paymentResponse에 추가
//            paymentResponse.putAll(response);
//            paymentResponse.put("status", "success");
//            paymentResponse.put("message", "결제 준비가 완료되었습니다.");
//        } catch (Exception e) {
//            // 예외 발생 시 응답에 오류 메시지 추가
//            paymentResponse.put("status", "error");
//            paymentResponse.put("message", "결제 준비 중 오류 발생: " + e.getMessage());
//        }
//        return paymentResponse; // 자동으로 JSON으로 변환되어 전송
//    }
//    @GetMapping("/order/pay/success")
//    @ResponseBody
//    public Map<String, String> paymentSuccess() {
//        System.out.println("결제가 성공적으로 완료되었습니다.");	
//        Map<String, String> response = new HashMap<>();
//        response.put("redirectUrl", "/user_order_list");
//        return response; // 결제 성공 처리
//    }
	@GetMapping("/order/pay/success")
	public String paymentSuccess() {
	    System.out.println("success: 결제가 성공적으로 완료되었습니다.");
	    return "usr/v1/pages/paySuccess"; // 결제 성공 페이지로 리디렉션
	}
//
    @GetMapping("/order/pay/cancel")
    public String paymentCancel() {
        return "usr/v1/pages/payCancel"; // 결제 취소 처리
    }

    @GetMapping("/order/pay/fail")
    public String paymentFail() {
        return "usr/v1/pages/payFailed"; // 결제 실패 처리
    }
//	@Autowired
//	KakaoPayService kakaoPayService;
//    
//    /**
//     * 결제요청
//     */
//	@GetMapping("/order/pay")
//    public KakaoReadyResponseDto readyToKakaoPay() {
//
//        return kakaoPayService.kakaoPayReady();
//    }
//
// // 결제 취소시 실행 url
// 	@GetMapping("/order/pay/cancel")
// 	public String payCancel() {
// 		return "redirect:/";
// 	}
//     
// 	// 결제 실패시 실행 url    	
// 	@GetMapping("/order/pay/fail")
// 	public String payFail() {
// 		return "redirect:/carts";
// 	}
}
