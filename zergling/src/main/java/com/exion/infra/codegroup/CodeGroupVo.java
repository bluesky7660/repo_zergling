package com.exion.infra.codegroup;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CodeGroupVo {
	private String seq;
	//검색
	private String searchKeyword; // 검색 키워드
    private Integer dateType;	//검색 날짜 종류
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateStart;		//날짜 시작일
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateEnd;		//날짜 종료일
    private String dateStart;		//날짜 시작일
    private String dateEnd;		//날짜 종료일
    private Integer keywordType;	//검색키워드 타입
    private Integer sDelNy;			//검색 조건[삭제] 
    private Integer sUseNy;			//검색 조건[사용]
    
    
    //페이지네이션
//    private int listCount; // 리스트의 총 개수
//    private int totalPages; // 총 페이지 수
//    private int currentPage = 1; // 현재 페이지
//    private int pageSize = 3; // 페이지 크기
//    private int limit = 3;    // 보여줄 페이지 갯수
//    private int offset;   // 페이지를 건너뛸 갯수
    //-------------
    public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	//	public Date getDateStart() {
//		return dateStart;
//	}
//	public void setDateStart(Date dateStart) {
//		this.dateStart = dateStart;
//	}
//	public Date getDateEnd() {
//		return dateEnd;
//	}
//	public void setDateEnd(Date dateEnd) {
//		this.dateEnd = dateEnd;
//	}
	public Integer getKeywordType() {
		return keywordType;
	}
	public void setKeywordType(Integer keywordType) {
		this.keywordType = keywordType;
	}
	public Integer getsDelNy() {
		return sDelNy;
	}
	public void setsDelNy(Integer sDelNy) {
		this.sDelNy = sDelNy;
	}
	public Integer getsUseNy() {
		return sUseNy;
	}
	public void setsUseNy(Integer sUseNy) {
		this.sUseNy = sUseNy;
	}
//	public int getListCount() {
//		return listCount;
//	}
//	public void setListCount(int listCount) {
//		this.listCount = listCount;
//	}
//	public int getTotalPages() {
//		return totalPages;
//	}
//	public void setTotalPages(int totalPages) {
//		this.totalPages = totalPages;
//	}
//	public int getCurrentPage() {
//		return currentPage;
//	}
//	public void setCurrentPage(int currentPage) {
//		this.currentPage = currentPage;
//	}
//	public int getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//	public int getLimit() {
//		return limit;
//	}
//	public void setLimit(int limit) {
//		this.limit = limit;
//	}
//	public int getOffset() {
//		return offset;
//	}
//	public void setOffset(int offset) {
//		this.offset = offset;
//	}
	
    
}
