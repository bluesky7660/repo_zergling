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
	
	public List<CodeDto> selectList(CodeVo vo){
		
		List<CodeDto> commonCode = codeDao.selectList(vo);
		return commonCode;
	}
//	public PagingResponseDto<CodeDto> selectList( CodeVo vo){
//		CodeVo params  = new CodeVo();
//        params.setLimit(vo.getLimit());
//        params.setOffset(vo.getOffset());
////        params.setDateType(vo.DateType);
////        params.setDateStart(dateStart);
////        params.setDateEnd(dateEnd);
//        params.setKeywordType(vo.getKeywordType());
//        params.setsDelNy(vo.getsDelNy());
//        params.setsUseNy(vo.getsUseNy());
//        params.setSearchKeyword(vo.getSearchKeyword());
//		List<CodeDto> list = codeDao.selectList(params);
//		int listCount = codeDao.listCount(vo);
//        int totalPages = (int) Math.ceil((double) listCount / vo.getPageSize());
//		return new PagingResponseDto<>(list, listCount, totalPages, params.getCurrentPage(), params.getPageSize(), params.getSearchKeyword() );
//	}
	public int insert(CodeDto codeDto) {
		return codeDao.insert(codeDto);
	}
//	public int listCount(CodeVo vo) {
//		return codeDao.listCount(vo);
//	}
	public CodeDto selectOne(CodeDto codeDto) {
		return codeDao.selectOne(codeDto);
	}
	public List<CodeDto> bageList() {
		return codeDao.bageList();
	}
	public List<CodeDto> prodTypeList() {
		return codeDao.prodTypeList();
	}
	public List<CodeDto> authorTypeList() {
		return codeDao.authorTypeList();
	}
	public int update(CodeDto codeDto) {
		return codeDao.update(codeDto);
	}
	public int uelete(CodeDto codeDto) {
		return codeDao.uelete(codeDto);
	}
	public int delete(CodeDto codeDto) {
		return codeDao.delete(codeDto);
	}
//	public PagingResponseDto<CodeDto> findAll(int page, int size, String searchKeyword) {
//        int offset = (page - 1) * size;
//        CodeDto params  = new CodeDto();
//        params.setLimit(size);
//        params.setOffset(offset);
//        params.setSearchKeyword(searchKeyword);
//        List<CodeDto> list = codeDao.selectList2(params);
//        int listCount = codeDao.listCount(searchKeyword);
//        int totalPages = (int) Math.ceil((double) listCount / size);
//        return new PagingResponseDto<>(list, listCount, totalPages, page, size, searchKeyword);
//    }
}
