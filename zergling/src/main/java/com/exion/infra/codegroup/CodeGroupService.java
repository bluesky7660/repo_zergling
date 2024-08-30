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
}
