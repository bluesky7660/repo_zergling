package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
	@Autowired
	AuthorDao authorDao;
	
	public List<AuthorDto> authorList(){
		return authorDao.authorList();
	}
	public AuthorDto authorOne(AuthorDto authorDto) {
		return authorDao.authorOne(authorDto);
	}
}
