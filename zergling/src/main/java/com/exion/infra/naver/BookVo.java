package com.exion.infra.naver;

public class BookVo {
	private String title;
	private String link;
	private String image;
	private String author;
	private String discount;
	private String publisher;
	private String pubdate;
	private String isbn;
	private String description;
//-------------------------------
	public String getAuthorsWithComma() {
		if (this.author != null && !this.author.isEmpty()) {
	        String[] authorsArray = this.author.split("\\^"); // ^로 분리
	        return String.join(", ", authorsArray); // ", "로 다시 합쳐서 반환
	    }
        return "저자 없음"; // 작가 정보가 없을 경우
    }

	// 작가 정보를 외 몇명 형식으로 표시하는 메서드
    public String getAuthorsWithOthers() {
    	if (this.author != null && !this.author.isEmpty()) {
            String[] authorsArray = this.author.split("\\^"); // ^로 분리
            if (authorsArray.length == 1) {
                return authorsArray[0]; // 작가가 한 명인 경우
            } else {
                return authorsArray[0] + " 외 " + (authorsArray.length - 1) + "명"; // 작가가 여러 명인 경우
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
