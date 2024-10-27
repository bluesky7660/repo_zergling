package com.exion.infra.youtube;

public class YouTubeChannelDto {
	//채널용
	private String ycSeq;
	private String ycId;
	private String ycName;
	private String subscribersCount;
    private String videosCount;
    private String thumbnailUrl;
    private String channelUrl;
//------------------------------
	public String getYcSeq() {
		return ycSeq;
	}
	public void setYcSeq(String ycSeq) {
		this.ycSeq = ycSeq;
	}
	public String getYcId() {
		return ycId;
	}
	public void setYcId(String ycId) {
		this.ycId = ycId;
	}
	public String getYcName() {
		return ycName;
	}
	public void setYcName(String ycName) {
		this.ycName = ycName;
	}
	public String getSubscribersCount() {
		return subscribersCount;
	}
	public void setSubscribersCount(String subscribersCount) {
		this.subscribersCount = subscribersCount;
	}
	public String getVideosCount() {
		return videosCount;
	}
	public void setVideosCount(String videosCount) {
		this.videosCount = videosCount;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	
}
