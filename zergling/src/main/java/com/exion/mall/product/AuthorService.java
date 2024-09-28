package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
	@Autowired
	AuthorDao authorDao;
	
	public List<AuthorDto> authorList(AuthorVo vo){
		return authorDao.authorList(vo);
	}
	public List<AuthorDto> authorUsrList(AuthorVo vo){
		return authorDao.authorUsrList(vo);
	}
	public List<AuthorDto> authorXdmList(AuthorVo vo){
		return authorDao.authorXdmList(vo);
	}
	public List<AuthorDto> jobList(){
		return authorDao.jobList();
	}
	public AuthorDto authorOne(AuthorDto authorDto) {
		return authorDao.authorOne(authorDto);
	}
	public int listCount(AuthorVo vo){
		return authorDao.listCount(vo);
	}
	public int insertAuthor(AuthorDto authorDto) {
		return authorDao.insertAuthor(authorDto);
	}
	public int update(AuthorDto authorDto) {
		return authorDao.update(authorDto);
	}
	public int uelete(AuthorDto authorDto) {
		return authorDao.uelete(authorDto);
	}
	public int delete(AuthorDto authorDto) {
		return authorDao.delete(authorDto);
	}
}
