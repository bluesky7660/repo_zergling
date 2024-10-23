package com.exion.infra.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exion.infra.util.Constants;

@Service
public class TrackingService {
	
	@Autowired
    private RestTemplate restTemplate;
//	private static final String TRACKING_API_URL = "https://api.tracker.delivery/tracking/{carrier}/{trackingNumber}";
	public TrackingResponse trackShipment(String carrier, String trackingNumber) {
//		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(Constants.TRACKING_API_URL, TrackingResponse.class, carrier, trackingNumber);
    }
}
