package com.exion.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {
	
	@Autowired	//다른 interface나 class 사용할때 
	private CodeGroupDao codeGroupDao;
	
	public List<CodeGroupDto> selectList() {
		List<CodeGroupDto> codeGroups = codeGroupDao.selectList();
		return codeGroups;
	}
}
