package com.exion.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exion.infra.codegroup.CodeGroupDto;
import com.exion.infra.codegroup.PagingResponseDto;

@Service
public class CodeService {

	@Autowired
	CodeDao codeDao;
	
	public List<CodeDto> selectList(){
		List<CodeDto> commonCode = codeDao.selectList();
		return commonCode;
	}
	public int insert(CodeDto codeDto) {
		return codeDao.insert(codeDto);
	}
	public CodeDto selectOne(CodeDto codeDto) {
		return codeDao.selectOne(codeDto);
	}
	public int update(CodeDto codeDto) {
		return codeDao.update(codeDto);
	}
	public PagingResponseDto<CodeDto> findAll(int page, int size, String searchKeyword) {
        int offset = (page - 1) * size;
        CodeDto params  = new CodeDto();
        params.setLimit(size);
        params.setOffset(offset);
        params.setSearchKeyword(searchKeyword);
        List<CodeDto> list = codeDao.selectList2(params);
        int listCount = codeDao.listCount(searchKeyword);
        int totalPages = (int) Math.ceil((double) listCount / size);
        return new PagingResponseDto<>(list, listCount, totalPages, page, size, searchKeyword);
    }
}
