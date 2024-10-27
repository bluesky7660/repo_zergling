package com.exion.infra.youtube;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeChannelDao {
	public List<YouTubeChannelDto> selectList();
}
