package com.exion.mall.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<AuthorDto> authorList = authorDao.authorUsrList(vo);
        Map<String, AuthorDto> authorMap = new HashMap<>();

        for (AuthorDto author : authorList) {
        	System.out.println("코드: " + author.getCodeName());
            // 작가 이름으로 맵에서 AuthorDto 찾기
            AuthorDto existingAuthor = authorMap.get(author.getName());

            // 기존 작가가 없으면 새로 추가
            if (existingAuthor == null) {
            	 existingAuthor = new AuthorDto();
                 existingAuthor.setName(author.getName());
                 existingAuthor.setCodeName(author.getCodeName());
                 existingAuthor.setAuthorType(author.getAuthorType());
                 existingAuthor.setAuthorInfo(author.getAuthorInfo());
                 authorMap.put(author.getName(), existingAuthor);
            }

            // 제목과 이미지 소스를 추가
            existingAuthor.addTitleImage(author.getTitle(), author.getImgSrc());
        }
        // Map의 값들을 리스트로 변환
        List<AuthorDto> resultList = new ArrayList<>(authorMap.values());
        for (AuthorDto author : resultList ) {
            System.out.println("List1: " + author.getName());
            System.out.println("List3: " + author.getTitleImageMap());
            for (Map.Entry<String, String> entry : author.getTitleImageMap().entrySet()) { 
            	System.out.println("작품 제목: " + entry.getKey());
            	System.out.println("작품 이미지번호: " + entry.getValue());
            }
        }
		return resultList;
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
