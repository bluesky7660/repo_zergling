package com.exion.infra.codegroup;

import java.util.List;

//import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGroupDao {
	public List<CodeGroupDto> selectList();
	public List<CodeGroupDto> selectList2(PagingResponseDto pagingResponseDto);
	public int insert(CodeGroupDto codeGroupDto);
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto);
	public int update(CodeGroupDto codeGroupDto);
//	public List<CodeGroupDto> findAll(@Param("limit") int limit, @Param("offset") int offset);
	public int listCount(PaginatedDto paginatedDto);
}
