package com.exion.infra.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exion.infra.member.MemberDto;

@Service
public class LoginService {
	@Autowired
	LoginDao loginDao;
	
	public MemberDto selectXdmOne(MemberDto memberDto) {
//		if(memberDto.getUserId()&&memberDto.getUserPassword())
		return loginDao.selectXdmOne(memberDto);
	}
}
