package com.exion.infra.youtube;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<YouTubeChannelDto> channels = youTubeService.channelSelectAllList();
     // 각 채널에 대한 상세 정보를 추가하여 업데이트
        for (YouTubeChannelDto channel : channels) {
            YouTubeChannelDto detailedChannelInfo = youTubeService.getChannelInfo(channel.getYcId());
            
            // 추가 정보를 채널 DTO에 설정
            channel.setSubscribersCount(detailedChannelInfo.getSubscribersCount());
            channel.setVideosCount(detailedChannelInfo.getVideosCount());
            channel.setChannelUrl(detailedChannelInfo.getChannelUrl());
            channel.setThumbnailUrl(detailedChannelInfo.getThumbnailUrl());
        }
        for (YouTubeChannelDto channel : channels) {
            System.out.println("채널 ID: " + channel.getYcId());
            System.out.println("채널 이미지: " + channel.getThumbnailUrl());
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
    
    @RequestMapping(value = "/v1/infra/youtube/youtubeChannelXdmList")
	public String youtubeChannelXdmList(Model model,@ModelAttribute("vo") YouTubeChannelVo youTubeChannelVo) {
//    	youTubeChannelVo.setDateStart(youTubeChannelVo.getDateStart() == null || youTubeChannelVo.getDateStart() == "" ? null : UtilDateTime.add00TimeString(youTubeChannelVo.getDateStart()));
//		youTubeChannelVo.setDateEnd(youTubeChannelVo.getDateEnd() == null || youTubeChannelVo.getDateEnd() == "" ? null : UtilDateTime.add59TimeString(youTubeChannelVo.getDateEnd()));
		youTubeChannelVo.setParamsPaging(youTubeService.channelsCount(youTubeChannelVo));
		model.addAttribute("list",youTubeService.channelSelectList(youTubeChannelVo));
//		model.addAttribute("channels", youTubeService.selectList());
//		System.out.println("youtubeChannelXdmList");
		return "xdm/v1/infra/youtube/youtubeChannelXdmList";
	}
    
//    @GetMapping("/searchChannel")
//    @ResponseBody
//    public YouTubeChannelDto searchChannel(@RequestParam("channelName") String channelName) {
//    	return youTubeService.searchChannelByName(channelName);
//    }
    @GetMapping("/searchChannel")
    @ResponseBody
    public ResponseEntity<?> searchChannel(@RequestParam("channelName") String channelName) {
    	
    	try {
    		YouTubeChannelDto channelDto = youTubeService.searchChannelByName(channelName);
    		// 각 필드를 개별적으로 출력
    		System.out.println("채널 ID: " + channelDto.getYcId());
    		System.out.println("채널 이름: " + channelDto.getYcName());
    		System.out.println("채널 설명: " + channelDto.getChannelsDescription());
    		System.out.println("구독자 수: " + channelDto.getSubscribersCount());
    		System.out.println("비디오 수: " + channelDto.getVideosCount());
    		System.out.println("썸네일 URL: " + channelDto.getThumbnailUrl());
    		System.out.println("채널 URL: " + channelDto.getChannelUrl());
    		youTubeService.channelInst(channelDto);
            return ResponseEntity.ok(channelDto);
        } catch (IllegalArgumentException e) {
        	System.out.println("이름틀림");
            // 정확한 채널 이름이 아닐 경우 에러 메시지 반환
        	Map<String, Object> response = new HashMap<>();
            response.put("match", false);
            response.put("message", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

	@RequestMapping(value = "/v1/test/channel")
	public String channel() {

//		System.out.println("youtubeChannelXdmList");
		return "/usr/v1/test/channel";
	}
    
	@RequestMapping(value = "/v1/infra/youtube/youtubeChannelXdmForm")
	public String youtubeChannelXdmForm() {

//		System.out.println("youtubeChannelXdmList");
		return "xdm/v1/infra/youtube/youtubeChannelXdmForm";
	}
    @RequestMapping(value = "/v1/infra/youtube/youtubeChannelXdmMfom")
	public String youtubeChannelXdmMfom(Model model, YouTubeChannelDto youTubeChannelDto) {
    	model.addAttribute("item", youTubeService.channelSelectOne(youTubeChannelDto));
//    	System.out.println("getUseNy:"+youTubeService.channelSelectOne(youTubeChannelDto).getUseNy());
//		System.out.println("youtubeChannelXdmMfom");
		return "xdm/v1/infra/youtube/youtubeChannelXdmMfom";
	}
}
