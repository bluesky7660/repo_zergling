package com.exion.infra.delivery;

import java.util.List;

public class TrackingResponse {
	private String status;
    private String carrier;
    private List<TrackingEvent> events;
//-------------------------------------
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public List<TrackingEvent> getEvents() {
		return events;
	}
	public void setEvents(List<TrackingEvent> events) {
		this.events = events;
	}
    
}
