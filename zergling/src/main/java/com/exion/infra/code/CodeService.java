package com.exion.infra.code;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class CodeService {

	@Autowired
	CodeDao codeDao;
	
	public List<CodeDto> selectList(CodeVo vo){
		
		List<CodeDto> commonCode = codeDao.selectList(vo);
		return commonCode;
	}
	public int insert(CodeDto codeDto) {
		return codeDao.insert(codeDto);
	}
	public int listCount(CodeVo vo) {
		return codeDao.listCount(vo);
	}
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
	public List<CodeDto> publisherList() {
		return codeDao.publisherList();
	}
	public List<CodeDto> genderList() {
		return codeDao.genderList();
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
	
	@PostConstruct
	public void selectListCachedCodeArrayList() throws Exception {
		List<CodeDto> codeListFromDb = (ArrayList<CodeDto>) codeDao.selectListCachedCodeArrayList();
//		codeListFromDb = (ArrayList<Code>) codeDao.selectListCachedCodeArrayList();
		CodeDto.cachedCodeArrayList.clear(); 
		CodeDto.cachedCodeArrayList.addAll(codeListFromDb);
		System.out.println("cachedCodeArrayList: " + CodeDto.cachedCodeArrayList.size() + " chached !");
	}
	
	public static void clear() throws Exception {
		CodeDto.cachedCodeArrayList.clear();
	}
	
	
	public static List<CodeDto> selectListCachedCode(String ifcgSeq) throws Exception {
		List<CodeDto> rt = new ArrayList<CodeDto>();
		for(CodeDto codeRow : CodeDto.cachedCodeArrayList) {
			if (codeRow.getCodeGroup_seq().equals(ifcgSeq)) {
				rt.add(codeRow);
			} else {
				// by pass
			}
		}
		return rt;
	}

	
	public static String selectOneCachedCode(int code) throws Exception {
		String rt = "";
		for(CodeDto codeRow : CodeDto.cachedCodeArrayList) {
			if (codeRow.getSeq().equals(Integer.toString(code))) {
				rt = codeRow.getCodeName();
			} else {
				// by pass
			}
		}
		return rt;
	}
}
