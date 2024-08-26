package com.exion.infra.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	@Autowired
	CodeDao dao;
	
	public void selectList() {
		dao.selectList();
	}
}
