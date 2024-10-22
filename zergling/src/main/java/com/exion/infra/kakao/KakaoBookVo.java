package com.exion.infra.kakao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoBookVo {
    private String title;
    private List<String> authors;
    private String publisher;
    private String thumbnail; // 책 이미지 URL
//-------------------------------------------
    // 추가된 메서드
    public String getAuthorsWithOthers() {
        if (authors != null && !authors.isEmpty()) {
            if (authors.size() == 1) {
                return authors.get(0); // 작가가 한 명인 경우
            } else {
                return authors.get(0) + " 외 " + (authors.size() - 1) + "명"; // 작가가 여러 명인 경우
            }
        }
        return "저자 없음"; // 작가 정보가 없을 경우
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
    
}
