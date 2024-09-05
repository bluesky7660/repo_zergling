package com.exion.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {
	@Autowired
	CodeGroupDao codeGroupDao;
	
//	public List<CodeGroupDto> selectList(){
//		List<CodeGroupDto> codeGroup = codeGroupDao.selectList();
//		return codeGroup;
//	}
	
	public List<CodeGroupDto> selectList(){
		return codeGroupDao.selectList();
	}
	public int insert(CodeGroupDto codeGroupDto) {
//		int result = codeGroupDao.insert(codeGroupDto);
//		return result;
		return codeGroupDao.insert(codeGroupDto);
	}
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
		return codeGroupDao.selectOne(codeGroupDto);
	}
//	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
//		CodeGroupDto dto = codeGroupDao.selectOne(codeGroupDto);
//		return dto;
//	}
	public int update(CodeGroupDto codeGroupDto) {
		return codeGroupDao.update(codeGroupDto);
	}
	
	public List<CodeGroupDto> findAll(int page, int size, String searchKeyword) {
		// 페이지 번호와 페이지 크기 설정
        int offset = (page - 1) * size;
        PaginatedDto params = new PaginatedDto(size, offset ,searchKeyword);

        List<CodeGroupDto> codeGroups = codeGroupDao.selectList2(params);

        // Set data for the view layer
        return codeGroups;
    }
	public int listCount(String searchKeyword) {
		PaginatedDto params = new PaginatedDto(0,0,searchKeyword);
		return codeGroupDao.listCount(params);
	}
}
