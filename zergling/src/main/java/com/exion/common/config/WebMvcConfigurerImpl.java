package com.exion.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.exion.common.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer{
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
//				.order(1)
				.addPathPatterns("/*/*/*/*Xdm*")
				.excludePathPatterns(
//						"/resources/**",
						"/usr/**"
						,"/xdm/**"
						,"/v1/infra/member/signupXdm"
						,"/v1/infra/member/loginXdm"
						,"/v1/infra/member/loginXdmProc"
//						,"/v1/mall/product/signup"
//						,"/v1/mall/product/login"
//						,"/v1/mall/product/index"
//						,"/v1/mall/product/signupInst"
//						,"/v1/mall/product/account_recovery"
						
		);
	}
	
}
