package com.exion.infra.codegroup;

import java.util.List;

public class PagingResponseDto {
	private List<CodeGroupDto> list;  // 검색 결과 리스트
    private int totalRows; // 총 행의 수
    private int totalPages; // 총 페이지 수
    private int currentPage; // 현재 페이지
    private int pageSize; // 페이지 크기
    private String searchKeyword; // 검색 키워드
    private int limit;	//보여줄 페이지 갯수
    private int offset;	//페이지를 건너뛸 갯수
    
    public PagingResponseDto(List<CodeGroupDto> list, int totalRows, int totalPages, int currentPage, int pageSize, String searchKeyword) {
        this.list = list;
        this.totalRows = totalRows;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.searchKeyword = searchKeyword;
    }
//--------------------------------------------

	public List<CodeGroupDto> getList() {
		return list;
	}

	public void setList(List<CodeGroupDto> list) {
		this.list = list;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
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
    
}
