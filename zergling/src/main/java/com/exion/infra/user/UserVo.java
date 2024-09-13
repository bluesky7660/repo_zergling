package com.exion.infra.user;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.exion.infra.codegroup.BaseVo;

public class UserVo extends BaseVo{
	private String seq;
	private String codeName;
	private Integer gender;
	private Integer admNy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
//	private String searchKeyword; // 검색 키워드
//    private Integer dateType;	//검색 날짜 종류
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateStart;		//날짜 시작일
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateEnd;		//날짜 종료일
//    private Integer keywordType;	//검색키워드 타입
//    private Integer sDelNy;			//검색 조건[삭제] 
//    private Integer sUseNy;			//검색 조건[사용]
//-----------------------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAdmNy() {
		return admNy;
	}
	public void setAdmNy(Integer admNy) {
		this.admNy = admNy;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}
