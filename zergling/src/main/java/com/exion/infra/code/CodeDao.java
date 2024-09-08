package com.exion.infra.code;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao {
	public List<CodeDto> selectList();
	public List<CodeDto> selectList2(CodeDto codeDto);
	public int insert(CodeDto codeDto);
	public CodeDto selectOne(CodeDto codeDto);
	public int update(CodeDto codeDto);
	public int listCount(String searchKeyword);
}
