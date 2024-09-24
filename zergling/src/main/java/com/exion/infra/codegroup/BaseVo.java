package com.exion.infra.codegroup;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.exion.infra.util.Constants;

public class BaseVo {
	
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
    private int thisPage = 1; 				//현재 페이지
    private int rowNumToShow = Constants.ROW_NUM_TO_SHOW;//	5;	// 화면에 보여줄 데이터 줄 갯수
	private int pageNumToShow = Constants.PAGE_NUM_TO_SHOW;//5;		// 화면에 보여줄 페이징 번호 갯수
    
    private int totalRows; 					//전체 데이터 갯수
    private int totalPages;					//전체페이지 번호
    private	int startPage;					//시작 페이지 번호
    private int endPage;					//마지막 페이지 번호
    
    private int startRnumForMysql = 0; 	// 쿼리 시작하는 row[index번호]
    //-------------
    public void setParamsPaging(int totalRows) {
		
//		setThisPage(3);

		setTotalRows(totalRows);

		if (getTotalRows() == 0) {
			setTotalPages(1);
		} else {
			setTotalPages(getTotalRows() / getRowNumToShow());
		}

		if (getTotalRows() % getRowNumToShow() > 0) {
			setTotalPages(getTotalPages() + 1);
		}

		if (getTotalPages() < getThisPage()) {
			setThisPage(getTotalPages());
		}
		
		setStartPage(((getThisPage() - 1) / getPageNumToShow()) * getPageNumToShow() + 1);

		setEndPage(getStartPage() + getPageNumToShow() - 1);

		if (getEndPage() > getTotalPages()) {
			setEndPage(getTotalPages());
		}
		
		//오라클용
//		setEndRnumForOracle((getRowNumToShow() * getThisPage()));
//		setStartRnumForOracle((getEndRnumForOracle() - getRowNumToShow()) + 1);
//		if (getStartRnumForOracle() < 1) setStartRnumForOracle(1);
		
		if (thisPage == 1) {
			setStartRnumForMysql(0);
		} else {
			setStartRnumForMysql((getRowNumToShow() * (getThisPage()-1)));
		}
		
	}
//-------------------------
    
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

//	public Date getDateStart() {
//		return dateStart;
//	}
//
//	public void setDateStart(Date dateStart) {
//		this.dateStart = dateStart;
//	}
//
//	public Date getDateEnd() {
//		return dateEnd;
//	}
//
//	public void setDateEnd(Date dateEnd) {
//		this.dateEnd = dateEnd;
//	}

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

	public int getThisPage() {
		return thisPage;
	}

	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	public int getRowNumToShow() {
		return rowNumToShow;
	}

	public void setRowNumToShow(int rowNumToShow) {
		this.rowNumToShow = rowNumToShow;
	}

	public int getPageNumToShow() {
		return pageNumToShow;
	}

	public void setPageNumToShow(int pageNumToShow) {
		this.pageNumToShow = pageNumToShow;
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

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public Integer getStartRnumForMysql() {
		return startRnumForMysql;
	}

	public void setStartRnumForMysql(Integer startRnumForMysql) {
		this.startRnumForMysql = startRnumForMysql;
	}
	
    
}
