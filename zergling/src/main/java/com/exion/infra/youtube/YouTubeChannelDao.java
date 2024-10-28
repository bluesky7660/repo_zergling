package com.exion.infra.youtube;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeChannelDao {
	public List<YouTubeChannelDto> channelSelectAllList();
	public List<YouTubeChannelDto> channelSelectList(YouTubeChannelVo vo);
	public int channelsCount(YouTubeChannelVo vo);
	public YouTubeChannelDto channelSelectOne(YouTubeChannelDto dto);
}
