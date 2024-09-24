package com.exion.infra.codegroup;

import java.util.List;

//import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGroupDao {
	public List<CodeGroupDto> selectList(CodeGroupVo vo);
	public List<CodeGroupDto> selectList();
	public List<CodeGroupDto> selectList2();
//	public List<CodeGroupDto> selectList2(CodeGroupDto codeGroupDto);
	public int insert(CodeGroupDto codeGroupDto);
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto);
	public int update(CodeGroupDto codeGroupDto);
//	public List<CodeGroupDto> findAll(@Param("limit") int limit, @Param("offset") int offset);
//	public int listCount(String searchKeyword);
//	public int listCount(CodeGroupDto codeGroupDto);
	public int listCount(CodeGroupVo vo);
	public int uelete(CodeGroupDto codeGroupDto);
	public int delete(CodeGroupDto codeGroupDto);
}
