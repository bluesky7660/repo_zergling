package com.exion.infra.code;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
	public List<CodeDto> selectList(CodeVo vo);
	public List<CodeDto> selectList2(CodeDto codeDto);
	public int insert(CodeDto codeDto);
	public CodeDto selectOne(CodeDto codeDto);
	public List<CodeDto> bageList();
	public List<CodeDto> prodTypeList();
	public int update(CodeDto codeDto);
	public int listCount(CodeVo vo);
	public int uelete(CodeDto codeDto);
	public int delete(CodeDto codeDto);
}
