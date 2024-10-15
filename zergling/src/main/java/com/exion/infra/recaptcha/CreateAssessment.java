//package com.exion.infra.recaptcha;
//
//import com.google.cloud.recaptchaenterprise.v1.RecaptchaEnterpriseServiceClient;
//import com.google.recaptchaenterprise.v1.Assessment;
//import com.google.recaptchaenterprise.v1.CreateAssessmentRequest;
//import com.google.recaptchaenterprise.v1.Event;
//import com.google.recaptchaenterprise.v1.ProjectName;
//import com.google.recaptchaenterprise.v1.RiskAnalysis.ClassificationReason;
//import java.io.IOException;
//
//public class CreateAssessment {
//
//  public static void main(String[] args) throws IOException {
//    // 할 일: 샘플을 실행하기 전에 토큰과 reCAPTCHA 작업 변수를 바꿉니다.
//    String projectID = "zergling-1728957105807";
//    String recaptchaKey = "6LcXtGEqAAAAABgJh77_leG_-5IH2RnduylOXTSK";
//    String token = "action-token";
//    String recaptchaAction = "action-name";
//
//    createAssessment(projectID, recaptchaKey, token, recaptchaAction);
//  }
//
//  /**
//   * UI 작업의 위험을 분석하는 평가를 만듭니다.
//   *
//   * @param projectID : Google Cloud 프로젝트 ID입니다.
//   * @param recaptchaKey : 사이트/앱과 연결된 reCAPTCHA 키입니다.
//   * @param token : 클라이언트에서 가져온 생성된 토큰입니다.
//   * @param recaptchaAction : 토큰에 해당하는 작업 이름입니다.
//   */
//  public static void createAssessment(
//      String projectID, String recaptchaKey, String token, String recaptchaAction)
//      throws IOException {
//    // reCAPTCHA 클라이언트를 만듭니다.
//    // 할 일: 클라이언트 생성 코드를 캐시하거나(권장) 메서드를 종료하기 전에 client.close()를 호출합니다.
//    try (RecaptchaEnterpriseServiceClient client = RecaptchaEnterpriseServiceClient.create()) {
//
//      // 추적할 이벤트의 속성을 설정합니다.
//      Event event = Event.newBuilder().setSiteKey(recaptchaKey).setToken(token).build();
//
//      // 평가 요청을 작성합니다.
//      CreateAssessmentRequest createAssessmentRequest =
//          CreateAssessmentRequest.newBuilder()
//              .setParent(ProjectName.of(projectID).toString())
//              .setAssessment(Assessment.newBuilder().setEvent(event).build())
//              .build();
//
//      Assessment response = client.createAssessment(createAssessmentRequest);
//
//      // 토큰이 유효한지 확인합니다.
//      if (!response.getTokenProperties().getValid()) {
//        System.out.println(
//            "The CreateAssessment call failed because the token was: "
//                + response.getTokenProperties().getInvalidReason().name());
//        return;
//      }
//
//      // 예상한 작업이 실행되었는지 확인합니다.
//      if (!response.getTokenProperties().getAction().equals(recaptchaAction)) {
//        System.out.println(
//            "The action attribute in reCAPTCHA tag is: "
//                + response.getTokenProperties().getAction());
//        System.out.println(
//            "The action attribute in the reCAPTCHA tag "
//                + "does not match the action ("
//                + recaptchaAction
//                + ") you are expecting to score");
//        return;
//      }
//
//      // 위험 점수와 이유를 가져옵니다.
//      // 평가 해석에 대한 자세한 내용은 다음을 참조하세요.
//      // https://cloud.google.com/recaptcha-enterprise/docs/interpret-assessment
//      for (ClassificationReason reason : response.getRiskAnalysis().getReasonsList()) {
//        System.out.println(reason);
//      }
//
//      float recaptchaScore = response.getRiskAnalysis().getScore();
//      System.out.println("The reCAPTCHA score is: " + recaptchaScore);
//
//      // 평가 이름(ID)을 가져옵니다. 평가에 주석을 추가하는 데 사용합니다.
//      String assessmentName = response.getName();
//      System.out.println(
//          "Assessment name: " + assessmentName.substring(assessmentName.lastIndexOf("/") + 1));
//    }
//  }
//}
