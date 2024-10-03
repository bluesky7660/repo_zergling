package com.exion.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.exion.common.interceptor.CheckLoginSessionInterceptor;
import com.exion.common.interceptor.UsrSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer{
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
//				.order(1)
				.addPathPatterns("/*/*/*/*Xdm*")
				.excludePathPatterns(
						"/resources/**"
						,"/usr/**"
						,"/xdm/**"
						,"/v1/infra/member/signupXdm"
						,"/v1/infra/member/loginXdm"
						,"/v1/infra/member/loginXdmProc"
						
		);
		registry.addInterceptor(new UsrSessionInterceptor())
		//		.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/resources/**"
						,"/usr/v1/assets/**"
						,"/xdm/**"
						,"/*/*/*/*Xdm*"
						,"/signup"
						,"/login"
						,"/index"
						,"/product_detail"
						,"/product_list"
						,"/signupInst"
						,"/loginUsrProc"
						,"/logoutUsrProc"
						,"/account_recovery"
						
		);
	}
	
}