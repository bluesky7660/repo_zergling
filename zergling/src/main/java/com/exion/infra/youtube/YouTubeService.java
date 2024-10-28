package com.exion.infra.youtube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exion.infra.util.Constants;

@Service
public class YouTubeService {
	
	@Value("${youtube.api.key}")
	private String apiKey;
	
	@Value("${youtube.api.key.channel}")
	private String channelApiKey;
	
	@Autowired
    YouTubeChannelDao youTubeChannelDao;
	
	private final String YOUTUBE_SEARCH_URL = "https://www.googleapis.com/youtube/v3/search";

	//단순 검색
//	public String searchVideos(String query) {
//	    RestTemplate restTemplate = new RestTemplate();
//	    String url = String.format(YOUTUBE_SEARCH_URL, query, apiKey);
//	    return restTemplate.getForObject(url, String.class);
//	}
	
	//상세
	private final String YOUTUBE_VIDEO_DETAILS_URL = "https://www.googleapis.com/youtube/v3/videos";


	//리스트용
    public List<Map<String, Object>> searchVideos(String query) {
    	RestTemplate restTemplate = new RestTemplate();
        List<Map<String, Object>> results = new ArrayList<>(); // 결과를 저장할 리스트
        String nextPageToken = ""; // 다음 페이지 토큰

        // 최대 5개의 결과를 찾기 위한 루프
        while (results.size() < 5) {
            // YouTube 검색 API 호출
            String searchUrl = String.format("%s?part=snippet&maxResults=10&q=%s&type=video&key=%s&pageToken=%s", 
                YOUTUBE_SEARCH_URL, query, apiKey, nextPageToken);
            System.out.println("searchUrl: " + searchUrl);
            String searchResponse = restTemplate.getForObject(searchUrl, String.class);
            JSONObject jsonResponse = new JSONObject(searchResponse);
            JSONArray videoItems = jsonResponse.getJSONArray("items");

            // 각 동영상에 대해 추가 정보 요청
            for (int i = 0; i < videoItems.length(); i++) {
                JSONObject videoItem = videoItems.getJSONObject(i);
                String videoId = videoItem.getJSONObject("id").getString("videoId");

                // 동영상 제목 확인
                String title = videoItem.getJSONObject("snippet").getString("title");

                // 제목에 "쓰는"이 포함되지 않은 경우에만 처리
                if (!title.contains("쓰는")&& !title.contains("쓰기")) {
                    // 동영상 상세 정보 요청
                    String videoDetailUrl = String.format("%s?part=statistics,snippet,contentDetails&id=%s&key=%s", 
                        YOUTUBE_VIDEO_DETAILS_URL, videoId, apiKey);
                    String videoDetailResponse = restTemplate.getForObject(videoDetailUrl, String.class);
                    JSONObject videoDetails = new JSONObject(videoDetailResponse);

                    if (videoDetails.getJSONArray("items").length() > 0) {
                        JSONObject details = videoDetails.getJSONArray("items").getJSONObject(0);
                        Map<String, Object> item = new HashMap<>(); // Map 객체 생성

                        // statistics 정보 추가
                        JSONObject statistics = details.getJSONObject("statistics");
                        item.put("viewCount", statistics.getString("viewCount"));
                        item.put("likeCount", statistics.has("likeCount") ? statistics.getString("likeCount") : "0");
                        item.put("commentCount", statistics.has("commentCount") ? statistics.getString("commentCount") : "0");

                        // snippet 정보 추가
                        JSONObject snippet = details.getJSONObject("snippet");
                        item.put("title", snippet.getString("title"));
                        item.put("channelTitle", snippet.getString("channelTitle"));
                        JSONObject thumbnails = snippet.getJSONObject("thumbnails");

						// 썸네일 URL 설정 로직
						String thumbnailUrl = "";
						if (thumbnails.has("maxres")) {
							// maxres 썸네일이 존재할 경우
							thumbnailUrl = thumbnails.getJSONObject("maxres").getString("url");
						} else if (thumbnails.has("standard")) {
							// maxres가 없고 standard 썸네일이 존재할 경우
							thumbnailUrl = thumbnails.getJSONObject("standard").getString("url");
						} else if (thumbnails.has("high")) {
							// maxres가 없고 high 썸네일이 존재할 경우
							thumbnailUrl = thumbnails.getJSONObject("high").getString("url"); 
						} else {
							// 셋 다 없을 경우 medium 썸네일  사용
							thumbnailUrl = thumbnails.getJSONObject("medium").getString("url");
						}
						
						item.put("thumbnailUrl", thumbnailUrl);

                        // 동영상 링크
                        item.put("videoUrl", "https://www.youtube.com/watch?v=" + videoId);

                        // 결과에 추가
                        results.add(item);
                    }
                }

                // 결과가 5개를 초과하면 루프 종료
                if (results.size() >= 5) {
                	System.out.println("루프test");
                    break;
                }
                System.out.println("test1");
            }

            // 다음 페이지가 있으면 페이지 토큰 업데이트
            nextPageToken = jsonResponse.optString("nextPageToken", "");
            if (nextPageToken.isEmpty()) {
                break; // 더 이상 결과가 없으면 종료
            }
            System.out.println("test2");
        }
        System.out.println("test3");
        return results; // List<Map<String, Object>> 반환
    }
    
    //채널용
    private final String VIDEO_DETAILS_URL = "https://www.googleapis.com/youtube/v3/videos";
    
    public List<YouTubeChannelDto> channelSelectList(YouTubeChannelVo vo) {
        return youTubeChannelDao.channelSelectList(vo);
    }
    public List<YouTubeChannelDto> channelSelectAllList() {
        return youTubeChannelDao.channelSelectAllList();
    }
    public int channelsCount(YouTubeChannelVo vo) {
    	return youTubeChannelDao.channelsCount(vo);
    }
    public YouTubeChannelDto channelSelectOne(YouTubeChannelDto dto) {
    	return youTubeChannelDao.channelSelectOne(dto);
    }
 // 채널 정보 및 최신 동영상 가져오기
    public Map<String, Object> getChannelDetails(String channelId) {
        // 채널 정보 가져오기
        YouTubeChannelDto channelInfo = getChannelInfo(channelId);
        System.out.println("channelInfo");
        // 최신 동영상 가져오기
        List<YouTubeVideoDto> latestVideos = getLatestVideos(channelId);
        System.out.println("latestVideos");
        // 채널 정보와 최신 동영상을 합쳐서 반환
        Map<String, Object> channelData = new HashMap<>();
        channelData.put("channelInfo", channelInfo);
        
        channelData.put("latestVideos", latestVideos);
        

        return channelData;
    }

    public YouTubeChannelDto getChannelInfo(String channelId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?part=snippet,statistics&id=%s&key=%s", Constants.YOUTUBE_CHANNEL_DETAILS_URL, channelId, channelApiKey);
        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(response);
        
        YouTubeChannelDto channelDTO = new YouTubeChannelDto();
        JSONObject channelData = jsonObject.getJSONArray("items").getJSONObject(0);
        
        channelDTO.setYcId(channelId);
        channelDTO.setYcName(channelData.getJSONObject("snippet").getString("title"));
        channelDTO.setChannelsDescription(channelData.getJSONObject("snippet").getString("description"));
        channelDTO.setSubscribersCount(channelData.getJSONObject("statistics").getString("subscriberCount"));
        channelDTO.setVideosCount(channelData.getJSONObject("statistics").getString("videoCount"));
        
        // 썸네일 URL 설정 로직
        JSONObject thumbnails = channelData.getJSONObject("snippet").getJSONObject("thumbnails");
		String thumbnailUrl = "";
		if (thumbnails.has("maxres")) {
			// maxres 썸네일이 존재할 경우
			thumbnailUrl = thumbnails.getJSONObject("maxres").getString("url");
		} else if (thumbnails.has("standard")) {
			// maxres가 없고 standard 썸네일이 존재할 경우
			thumbnailUrl = thumbnails.getJSONObject("standard").getString("url");
		} else if (thumbnails.has("high")) {
			// maxres가 없고 high 썸네일이 존재할 경우
			thumbnailUrl = thumbnails.getJSONObject("high").getString("url"); 
		} else {
			// 셋 다 없을 경우 medium 썸네일  사용
			thumbnailUrl = thumbnails.getJSONObject("medium").getString("url");
		}
		channelDTO.setThumbnailUrl(thumbnailUrl);
        String channelUrl = String.format("https://www.youtube.com/channel/%s", channelId);
        channelDTO.setChannelUrl(channelUrl);
        
        return channelDTO;
    }

    private List<YouTubeVideoDto> getLatestVideos(String channelId) {
        RestTemplate restTemplate = new RestTemplate();
        String searchUrl = String.format("%s?part=snippet&channelId=%s&maxResults=5&order=date&type=video&key=%s", 
        		YOUTUBE_SEARCH_URL, channelId, channelApiKey);
        
        // 동영상 검색 API 호출
        String searchResponse = restTemplate.getForObject(searchUrl, String.class);
        JSONObject searchJsonObject = new JSONObject(searchResponse);
        
        List<YouTubeVideoDto> videos = new ArrayList<>();
        
        // 동영상 ID를 저장할 리스트
        List<String> videoIds = new ArrayList<>();
        
        // 검색 결과에서 동영상 ID와 기본 정보 추출
        searchJsonObject.getJSONArray("items").forEach(item -> {
            JSONObject videoData = (JSONObject) item;
            String videoId = videoData.getJSONObject("id").getString("videoId");
            videoIds.add(videoId); // 동영상 ID 추가

            YouTubeVideoDto videoDTO = new YouTubeVideoDto();
            videoDTO.setVideoUrl("https://www.youtube.com/watch?v=" + videoId);
            videoDTO.setTitle(videoData.getJSONObject("snippet").getString("title"));
            // 썸네일 URL 설정 로직
            JSONObject thumbnails = videoData.getJSONObject("snippet").getJSONObject("thumbnails");
			String thumbnailUrl = "";
			if (thumbnails.has("maxres")) {
				// maxres 썸네일이 존재할 경우
				thumbnailUrl = thumbnails.getJSONObject("maxres").getString("url");
			} else if (thumbnails.has("standard")) {
				// maxres가 없고 standard 썸네일이 존재할 경우
				thumbnailUrl = thumbnails.getJSONObject("standard").getString("url");
			} else if (thumbnails.has("high")) {
				// maxres가 없고 high 썸네일이 존재할 경우
				thumbnailUrl = thumbnails.getJSONObject("high").getString("url"); 
			} else {
				// 셋 다 없을 경우 medium 썸네일  사용
				thumbnailUrl = thumbnails.getJSONObject("medium").getString("url");
			}
            videoDTO.setThumbnailUrl(thumbnailUrl);
            
            videos.add(videoDTO); // 기본 정보 설정 후 리스트에 추가
        });

        // 동영상 ID를 기반으로 통계 정보 가져오기
        if (!videoIds.isEmpty()) {
            String videoIdsParam = String.join(",", videoIds); // 동영상 ID를 콤마로 구분하여 문자열로 변환
            String statsUrl = String.format("%s?part=statistics&id=%s&key=%s", 
                                             YOUTUBE_VIDEO_DETAILS_URL, videoIdsParam, channelApiKey);
            String statsResponse = restTemplate.getForObject(statsUrl, String.class);
            JSONObject statsJsonObject = new JSONObject(statsResponse);
            
            // 통계 정보 설정
            statsJsonObject.getJSONArray("items").forEach(item -> {
                JSONObject statisticsData = (JSONObject) item;
                String videoId = statisticsData.getString("id");
                for (YouTubeVideoDto videoDTO : videos) {
                    if (videoDTO.getVideoUrl().contains(videoId)) {
                        JSONObject statistics = statisticsData.getJSONObject("statistics");
                        videoDTO.setViewCount(statistics.has("viewCount") ? statistics.getString("viewCount") : "조회수 미지원");
                        videoDTO.setLikeCount(statistics.has("likeCount") ? statistics.getString("likeCount") : "좋아요 미지원");
                    }
                }
            });
        }

        return videos;
    }

//    public List<Map<String, Object>> getAllChannelsLatestVideos() {
//    	
//        List<Map<String, Object>> response = new ArrayList<>();
//        List<YouTubeChannelDto> channels = youTubeChannelDao.selectList();
//        RestTemplate restTemplate = new RestTemplate();
//
//        for (YouTubeChannelDto channel : channels) {
//            Map<String, Object> channelData = new HashMap<>();
//            String searchUrl = String.format(
//                "https://www.googleapis.com/youtube/v3/search?key=%s&channelId=%s&part=id&type=video&order=date&maxResults=5",
//                channelApiKey, channel.getYcId()
//            );
//            String responseSearch = restTemplate.getForObject(searchUrl, String.class);
//            JSONArray items = new JSONObject(responseSearch).getJSONArray("items");
//
//            List<JSONObject> latestVideos = new ArrayList<>();
//            for (int i = 0; i < items.length(); i++) {
//                String videoId = items.getJSONObject(i).getJSONObject("id").getString("videoId");
//                String videoDetailUrl = String.format(
//                    "%s?part=snippet&id=%s&key=%s", VIDEO_DETAILS_URL, videoId, channelApiKey
//                );
//                String videoDetailResponse = restTemplate.getForObject(videoDetailUrl, String.class);
//                JSONObject videoDetail = new JSONObject(videoDetailResponse).getJSONArray("items").getJSONObject(0);
//                latestVideos.add(videoDetail);
//            }
//
//            channelData.put("channelName", channel.getYcName());
//            channelData.put("channelId", channel.getYcId());
//            channelData.put("latestVideos", latestVideos);
//            response.add(channelData);
//        }
//
//        return response;
//    }
}
