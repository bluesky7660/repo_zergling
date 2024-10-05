package com.exion.infra.code;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
	public List<CodeDto> selectList(CodeVo vo);
//	public List<CodeDto> selectList2(CodeDto codeDto);
	public int insert(CodeDto codeDto);
	public CodeDto selectOne(CodeDto codeDto);
	public List<CodeDto> bageList();
	public List<CodeDto> prodTypeList();
	public List<CodeDto> authorTypeList();
	public List<CodeDto> publisherList();
	public List<CodeDto> genderList();
	public List<CodeDto> tagsList();
	public int update(CodeDto codeDto);
	public int listCount(CodeVo vo);
	public int uelete(CodeDto codeDto);
	public int delete(CodeDto codeDto);
	
	public List<CodeDto> selectListCachedCodeArrayList();
}
