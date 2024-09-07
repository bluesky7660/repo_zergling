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
		// 리스트를 건너뛸 갯수 (1[현재페이지]-1)*5 = 0, (2-1)*5 = 5
		//select 에서 OFFSET절에서 사용할 값으로 5면 리스트5번째까지 건너뛰고 6번째부터 출력
        int offset = (page - 1) * size;
        //page 현재페이지,size 보여줄 리스트의 갯수 - PaginatedDto 의 int limit에 size값이 들어간다.
        PagingResponseDto params = new PagingResponseDto(size, offset ,searchKeyword);
        // Set data for the view layer
        return codeGroupDao.selectList2(params);
    }
	public int listCount(String searchKeyword) {
		PaginatedDto params = new PaginatedDto(0,0,searchKeyword);
		return codeGroupDao.listCount(params);
	}
}
