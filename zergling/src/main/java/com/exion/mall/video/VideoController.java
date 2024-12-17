package com.exion.mall.video;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exion.infra.codegroup.BaseVo;
import com.exion.infra.youtube.YouTubeService;

@Controller
public class VideoController {
	
	@Autowired
    YouTubeService youTubeService;
	
	@Autowired
	VideoService videoService;
	
	@RequestMapping(value = "book_review_video_list")
	public String videoList(Model model ,@ModelAttribute("vo") BaseVo baseVo) {
		model.addAttribute("reviewVideoList", videoService.reviewVideoList());
		System.out.println("book_review_video_list");
		return "usr/v1/pages/book_review_video_list";
	}
	
	@RequestMapping(value = "reviewVideoSelect")
	@ResponseBody
	public Map<String, Object> reviewVideoSelect(VideoDto videoDto) {
		VideoDto item = videoService.reviewVideoOne(videoDto);
		Map<String, Object> responseMap = new HashMap<>();
//		if(item) {
//			
//		}
		responseMap.put("item", item);
		System.out.println("reviewVideoSelect");
		
		return responseMap;
	}
	
	@RequestMapping(value = "channels_video_list")
	public String channelsVideoList(Model model, @ModelAttribute("vo") BaseVo baseVo) {
		model.addAttribute("channels", youTubeService.channelSelectAllList());
		System.out.println("channels_video_list");
		return "usr/v1/pages/channels_video_list";
	}
}
