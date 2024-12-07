package com.exion.infra.youtube;

import java.util.Date;

public class YouTubeChannelDto {
	//채널용
	private String ycSeq;
	private String ycId;
	private String ycName;
	private String ycUrl;
	private String channelsDescription;
	private String subscribersCount;
    private String videosCount;
    private String thumbnailUrl;
    private String channelUrl;
    
    
    private Integer delNy;
    private Integer useNy;
    private Date regDate;
	private Date modDate;
	private String channelImageUrl;
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
	public String getChannelsDescription() {
		return channelsDescription;
	}
	public void setChannelsDescription(String channelsDescription) {
		this.channelsDescription = channelsDescription;
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
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	public Integer getUseNy() {
		return useNy;
	}
	public void setUseNy(Integer useNy) {
		this.useNy = useNy;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getYcUrl() {
		return ycUrl;
	}
	public void setYcUrl(String ycUrl) {
		this.ycUrl = ycUrl;
	}
	public String getChannelImageUrl() {
		return channelImageUrl;
	}
	public void setChannelImageUrl(String channelImageUrl) {
		this.channelImageUrl = channelImageUrl;
	}
	
}
