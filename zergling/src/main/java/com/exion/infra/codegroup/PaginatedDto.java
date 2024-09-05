package com.exion.infra.codegroup;

public class PaginatedDto {
	private int limit;
    private int offset;
    private String searchKeyword;
//------------------------
    public PaginatedDto(int limit, int offset, String searchKeyword) {
        this.limit = limit;
        this.offset = offset;
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
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
		
}
