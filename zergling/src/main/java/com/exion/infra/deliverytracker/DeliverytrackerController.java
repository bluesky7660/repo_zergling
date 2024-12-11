package com.exion.infra.deliverytracker;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.exion.infra.util.Constants;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class DeliverytrackerController {
	@Value("${delivery.tracker.id}")
	private String clientId;
	
	@Value("${delivery.tracker.secret}")
	private String clientSecret;
	
	@RequestMapping("/deliveryTracker")
	@ResponseBody
	public ResponseEntity<DeliveryTrackerResponse> deliveryTracker(@RequestBody DeliveryTrackerRequest deliveryTrackerRequest) {
		System.out.println("Request received: " + deliveryTrackerRequest.getCarrierId()+", "+deliveryTrackerRequest.getTrackingNumber());
		//api query
		String query = "query Track($carrierId: ID!, $trackingNumber: String!) { "
                + "track(carrierId: $carrierId, trackingNumber: $trackingNumber) { "
                + "lastEvent { time status { code name } description } "
                + "events(last: 10) { edges { node { time status { code name } description } } } } "
                + "}";
		
		//rest template
		RestTemplate restTemplate = new RestTemplate();
		
		//택배 회사와 송장번호
		JSONObject variables = new JSONObject();
        variables.put("carrierId", deliveryTrackerRequest.getCarrierId());
        variables.put("trackingNumber", deliveryTrackerRequest.getTrackingNumber());
		
		//api body
		JSONObject requestBody = new JSONObject();
        requestBody.put("query", query);    
        requestBody.put("variables", variables);

		
        //api header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "TRACKQL-API-KEY "+clientId+":"+clientSecret);
        System.out.println("Response headers: " + headers);
        //API 호출
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
        
        ResponseEntity<DeliveryTrackerResponse> response = restTemplate.exchange(Constants.GRAPHQL_URL, HttpMethod.POST, entity, DeliveryTrackerResponse.class);
        
        System.out.println("Response Body: " + response.getBody().getData().getTrack());
        
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        } else {
            throw new RuntimeException("API 호출 실패: " + response.getStatusCode());
        }
	}
	
	@RequestMapping("/deliveryTrackerPopup")
	public String deliveryTrackerPopup() {
		return "usr/v1/popup/delivery_tracker";
	}
	
	
}
