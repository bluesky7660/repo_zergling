package com.exion.infra.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {
	@Autowired
	CodeGroupDao dao;
	
	public void selectList() {
		dao.selectList();
	}
}
