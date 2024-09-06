package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao {
	public List<AuthorDto> authorList();
	public AuthorDto authorOne(AuthorDto authorDto);
}
