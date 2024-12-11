package com.exion.infra.deliverytracker;

public class DeliveryTrackerRequest {
	private String carrierId;
	private String trackingNumber;
//-------------------------------------
	public String getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	
}
