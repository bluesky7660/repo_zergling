package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao {
	public List<AuthorDto> authorList(AuthorVo vo);
	public List<AuthorDto> authorXdmList(AuthorVo vo);
	public List<AuthorDto> authorUsrList(AuthorVo vo);
	public List<AuthorDto> prodAuthorList(AuthorVo vo);
	public AuthorDto authorOne(AuthorDto authorDto);
	public List<AuthorDto> jobList();
	public int listCount(AuthorVo vo);
	public int insertAuthor(AuthorDto authorDto);
	public int update(AuthorDto authorDto);
	public int uelete(AuthorDto authorDto);
	public int delete(AuthorDto authorDto);
	
	public int insertUploaded(AuthorDto authorDto);
}
