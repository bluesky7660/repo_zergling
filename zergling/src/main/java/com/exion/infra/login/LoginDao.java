package com.exion.infra.login;

import org.springframework.stereotype.Repository;

import com.exion.infra.member.MemberDto;

@Repository
public interface LoginDao {
	public MemberDto selectXdmOne(MemberDto memberDto);
}
