package com.exion.infra.codegroup;

import java.util.Date;
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
	public int uelete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.uelete(codeGroupDto);
	}
	public int delete(CodeGroupDto codeGroupDto) {
		return codeGroupDao.delete(codeGroupDto);
	}
	public PagingResponseDto<CodeGroupDto> findAll(int page, int size, int dateType, Date dateStart, Date dateEnd,
    		int keywordType, int sDelNy, int sUseNy, String searchKeyword) {
		// 리스트를 건너뛸 갯수 (1[현재페이지]-1)*5 = 0, (2-1)*5 = 5
		//select 에서 OFFSET절에서 사용할 값으로 5면 리스트5번째까지 건너뛰고 6번째부터 출력
        int offset = (page - 1) * size;
        CodeGroupDto params  = new CodeGroupDto();
        params.setLimit(size);
        params.setOffset(offset);
        params.setDateType(dateType);
        params.setDateStart(dateStart);
        params.setDateEnd(dateEnd);
        params.setKeywordType(keywordType);
        params.setsDelNy(sDelNy);
        params.setsUseNy(sUseNy);
        params.setSearchKeyword(searchKeyword);
        List<CodeGroupDto> list = codeGroupDao.selectList2(params);
        int listCount = codeGroupDao.listCount(searchKeyword);
        int totalPages = (int) Math.ceil((double) listCount / size);
        return new PagingResponseDto<>(list, listCount, totalPages, page, size, searchKeyword);
    }
//	public int listCount(String searchKeyword) {
//		PagingResponseDto params = new PagingResponseDto(0,0,searchKeyword);
//		return 
//	}

}
