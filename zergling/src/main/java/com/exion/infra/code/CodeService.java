package com.exion.infra.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	@Autowired
	private CodeDao codeDao;
	
	public List<CodeDto> selectList() {
		List<CodeDto> commoncode = codeDao.selectList();
		return commoncode;
	}
}
