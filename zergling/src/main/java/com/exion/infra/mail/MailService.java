package com.exion.infra.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	 public void sendMailSimple() {
    	SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    	
    	simpleMailMessage.setTo("chlee7660@naver.com");
    	simpleMailMessage.setSubject("안녕하세요");
//	    simpleMailMessage.setFrom("wjsgusfhr324@gmail.com"); 
    	//gmail은 수정 불가 
    	simpleMailMessage.setText("안녕하세요\n\n 잘지내고 계시죠");

    	javaMailSender.send(simpleMailMessage);

    }
}
