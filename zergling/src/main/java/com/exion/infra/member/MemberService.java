package com.exion.infra.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;
	
	public List<MemberDto> selectMember(MemberVo vo){
		List<MemberDto> members = memberDao.selectMember(vo);
		return members;
	}
	public List<MemberDto> selectXdmMember(MemberVo vo){
		List<MemberDto> members = memberDao.selectXdmMember(vo);
		return members;
	}
	public int listCount(MemberVo vo) {
		return memberDao.listCount(vo);
	}
	public int insert(MemberDto memberDto) {
		return memberDao.insert(memberDto);
	}
	
	public MemberDto selectOne(MemberDto memberDto) {
		return memberDao.selectOne(memberDto);
	}
	public MemberDto selectXdmOne(MemberDto memberDto) {
		return memberDao.selectXdmOne(memberDto);
	}
	public int insertUsr(MemberDto memberDto) {
		return memberDao.insertUsr(memberDto);
	}
	public int update(MemberDto memberDto) {
		System.out.println("그룹이름: "+memberDto.getSeq());
		return memberDao.update(memberDto);
	}
}
