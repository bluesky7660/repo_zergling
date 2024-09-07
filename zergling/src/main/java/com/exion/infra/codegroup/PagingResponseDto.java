package com.exion.infra.codegroup;

import java.util.List;

public class PagingResponseDto<T> {
	private List<T> list;  // 검색 결과 리스트
    private int listCount; // 리스트의 총 개수
    private int totalPages; // 총 페이지 수
    private int currentPage; // 현재 페이지
    private int pageSize; // 페이지 크기
    private String searchKeyword; // 검색 키워드
    private int limit;    // 보여줄 페이지 갯수
    private int offset;   // 페이지를 건너뛸 갯수

    // 기본 생성자
    public PagingResponseDto() {}

    public PagingResponseDto(int limit, int offset, String searchKeyword) {
        this.limit = limit;
        this.offset = offset;
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
