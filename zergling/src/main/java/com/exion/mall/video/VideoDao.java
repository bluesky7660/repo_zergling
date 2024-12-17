package com.exion.mall.video;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface VideoDao {
	public List<VideoDto> reviewVideoList();
	public VideoDto reviewVideoOne(VideoDto videoDto);
}
