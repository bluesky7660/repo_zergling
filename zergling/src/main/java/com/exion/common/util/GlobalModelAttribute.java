package com.exion.common.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.exion.infra.codegroup.BaseVo;

@ControllerAdvice
public class GlobalModelAttribute {
	@ModelAttribute("shVo") // 모든 컨트롤러에 "vo"라는 이름으로 모델에 추가
    public BaseVo searchModelClass() {
		BaseVo vo = new BaseVo();
        // 필요한 초기화 로직을 추가
        // 예를 들어: vo.setAttribute1("값");
        return vo; // 반환된 객체가 모든 페이지에서 사용 가능
    }
}
