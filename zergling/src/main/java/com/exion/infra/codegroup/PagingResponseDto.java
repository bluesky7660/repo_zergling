package com.exion.infra.codegroup;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class PagingResponseDto<T> {
	private List<T> list;  // 검색 결과 리스트
    private int listCount; // 리스트의 총 개수
    private int totalPages; // 총 페이지 수
    private int currentPage; // 현재 페이지
    private int pageSize; // 페이지 크기
    
    private String searchKeyword; // 검색 키워드
    private int dateType;	//검색 날짜 종류
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;		//날짜 시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;		//날짜 종료일
    private int keywordType;	//검색키워드 타입
    private int sDelNy;			//검색 조건[삭제] 
    private int sUseNy;			//검색 조건[사용]
    
    private int limit;    // 보여줄 페이지 갯수
    private int offset;   // 페이지를 건너뛸 갯수

    // 기본 생성자
    public PagingResponseDto() {}

    public PagingResponseDto(int limit, int offset, int dateType, Date dateStart, Date dateEnd,
    		int keywordType, int sDelNy, int sUseNy, String searchKeyword ) {
        this.limit = limit;
        this.offset = offset;
        this.dateType = dateType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.keywordType = keywordType;
        this.sDelNy = sDelNy;
        this.sUseNy = sUseNy;
        this.searchKeyword = searchKeyword;
    }

    public PagingResponseDto(List<T> list, int listCount, int totalPages, int currentPage, int pageSize, String searchKeyword) {
        this.list = list;
        this.listCount = listCount;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.searchKeyword = searchKeyword;
    }
    // Getters and Setters

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(int keywordType) {
		this.keywordType = keywordType;
	}

	public int getsDelNy() {
		return sDelNy;
	}

	public void setsDelNy(int sDelNy) {
		this.sDelNy = sDelNy;
	}

	public int getsUseNy() {
		return sUseNy;
	}

	public void setsUseNy(int sUseNy) {
		this.sUseNy = sUseNy;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
