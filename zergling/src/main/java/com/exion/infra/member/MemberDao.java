package com.exion.infra.member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
	public List<MemberDto> selectMember(MemberVo vo);
	public List<MemberDto> selectXdmMember(MemberVo vo);
	public MemberDto selectXdmOne(MemberDto memberDto);
	public MemberDto selectUsrOne(MemberDto memberDto);
	public int insert(MemberDto memberDto);
	public int listCount(MemberVo vo);
	public MemberDto selectOne(MemberDto memberDto);
	public int insertUsr(MemberDto memberDto);
	public int update(MemberDto memberDto);
}
