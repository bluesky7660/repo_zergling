package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao {
	public List<AuthorDto> authorList();
	public AuthorDto authorOne(AuthorDto authorDto);
	public List<AuthorDto> jobList();
	public int insertAuthor(AuthorDto authorDto);
	public int update(AuthorDto authorDto);
	public int uelete(AuthorDto authorDto);
	public int delete(AuthorDto authorDto);
}
