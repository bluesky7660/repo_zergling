package com.exion.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.exion.infra.util.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;

public class UsrSessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		HttpSession session = request.getSession();
//		String requestURL = request.getRequestURL().toString();
//		String refererURL = request.getHeader("referer");
//		String queryString = request.getQueryString();
//		System.out.println("requestURL: "+ requestURL);
//		System.out.println("refererURL: "+ refererURL);
//		System.out.println("seq: " + session.getAttribute("sessSeqXdm"));
//		System.out.println("이름: " + session.getAttribute("sessNameXdm"));
//		System.out.println("주소: " + session.getAttribute("prevPage"));
		System.out.println("wjqiihqui");
//		System.out.println("seq: "+request.getSession().getAttribute("sessSeqXdm"));
		if(request.getSession().getAttribute("sessSeqXdm") != null) {
			//by pass
		}else {
			
//			if(requestURL.contains("product_buy")) {
//				session.setAttribute("prevPage", requestURL);
//			}else {
////				session.setAttribute("prevPage", null);
//			}
//			session.setAttribute("prevPage", null);
//			System.out.println("sessionURL: "+ session.getAttribute("prevPage"));
			response.sendRedirect(Constants.URL_USRLOGINFORM);
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
