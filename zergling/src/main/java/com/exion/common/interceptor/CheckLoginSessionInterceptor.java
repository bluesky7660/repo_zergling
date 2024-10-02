package com.exion.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.exion.infra.util.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginSessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("dsadasdas");
		if(request.getSession().getAttribute("sessSeqXdm") != null) {
			//by pass
		}else {
			response.sendRedirect(Constants.URL_LOGINFORM);
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
