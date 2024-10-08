package com.exion.infra.kakao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.common.util.SessionUtils;

@Controller
@RequestMapping("/order")
public class KakaoPayController {
	
	private final KakaoPayService kakaoPayService;

    @Autowired
    public KakaoPayController(KakaoPayService kakaoPayService) {
        this.kakaoPayService = kakaoPayService;
    }

	@PostMapping("/pay/ready")
	@ResponseBody
    public ReadyResponse payReady(@RequestBody OrderCreateForm orderCreateForm) {
        
        String name = orderCreateForm.getName();
        int totalPrice = orderCreateForm.getTotalPrice();
        
        System.out.println("주문 상품 이름: " + name);
        System.out.println("주문 금액: " + totalPrice);

        // 카카오 결제 준비하기
        System.out.println("결제 고유번호2: " + kakaoPayService.payReady(name, totalPrice).getTid());
        ReadyResponse readyResponse = kakaoPayService.payReady(name, totalPrice);
        // 세션에 결제 고유번호(tid) 저장
        System.out.println("결제 고유번호: " + readyResponse.getTid());
        SessionUtils.addAttribute("tid", readyResponse.getTid());
        

        return readyResponse;
    }

    @GetMapping("/pay/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken) {
    
        String tid = SessionUtils.getStringAttributeValue("tid");
        System.out.println("결제승인 요청을 인증하는 토큰: " + pgToken);
        System.out.println("결제 고유번호: " + tid);

        // 카카오 결제 요청하기
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);

        return "/pay/completed";
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
//    @GetMapping("/payment/success")
//    public String paymentSuccess() {
//        return "결제가 성공적으로 완료되었습니다."; // 결제 성공 처리
//    }
//
//    @GetMapping("/payment/cancel")
//    public String paymentCancel() {
//        return "결제가 취소되었습니다."; // 결제 취소 처리
//    }
//
//    @GetMapping("/payment/fail")
//    public String paymentFail() {
//        return "결제에 실패하였습니다."; // 결제 실패 처리
//    }
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
