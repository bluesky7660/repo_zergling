package com.exion.mall.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
	@Autowired
	VideoDao videoDao;
	
	public List<VideoDto> reviewVideoList(){
		return videoDao.reviewVideoList();
	}
	public VideoDto reviewVideoOne(VideoDto videoDto){
		return videoDao.reviewVideoOne(videoDto);
	}
}
