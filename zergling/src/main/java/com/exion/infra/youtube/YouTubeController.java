package com.exion.infra.youtube;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YouTubeController {

	@Autowired
	YouTubeService youTubeService;
	
	@GetMapping("/search")
    public ResponseEntity<?> search() {
		try {
			List<Map<String, Object>> result;
            result = youTubeService.searchVideos("도서서평");
            System.out.println("result: " + result); // 결과 출력
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();  // 콘솔에 에러를 출력
            return new ResponseEntity<>("An error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/channels")
    public ResponseEntity<?> getChannels() {
		System.out.println("getChannels");
        List<YouTubeChannelDto> channels = youTubeService.selectList();
     // 각 채널에 대한 상세 정보를 추가하여 업데이트
        for (YouTubeChannelDto channel : channels) {
            YouTubeChannelDto detailedChannelInfo = youTubeService.getChannelInfo(channel.getYcId());
            
            // 추가 정보를 채널 DTO에 설정
            channel.setSubscribersCount(detailedChannelInfo.getSubscribersCount());
            channel.setVideosCount(detailedChannelInfo.getVideosCount());
            channel.setChannelUrl(detailedChannelInfo.getChannelUrl()); // URL도 업데이트
        }
        for (YouTubeChannelDto channel : channels) {
            System.out.println("채널 ID: " + channel.getYcId());
            System.out.println("채널 이름: " + channel.getYcName());
            System.out.println("구독자 수: " + channel.getSubscribersCount());
            System.out.println("영상 수: " + channel.getVideosCount());
            System.out.println("채널 링크: " + channel.getChannelUrl());
        }
        return new ResponseEntity<>(channels, HttpStatus.OK);
    }

    @GetMapping("/channel/details")
    public ResponseEntity<?> getChannelVideos(@RequestParam("channelId") String channelId) {
    	System.out.println("getChannelVideos");
    	Map<String, Object> videos = youTubeService.getChannelDetails(channelId);
    	System.out.println("테스트:"+videos);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
}
