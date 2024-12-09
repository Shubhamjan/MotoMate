package com.demo.dto;

import com.demo.entities.ServiceStatus;

public class ServiceStatusDto {
	
	private Long bookingId;
	
	private String serviceStatus;
	
	private double serviceFee;

	public ServiceStatusDto() {
		super();
	}

	public ServiceStatusDto(Long bookingId, String serviceStatus, double serviceFee) {
		super();
		this.bookingId = bookingId;
		this.serviceStatus = serviceStatus;
		this.serviceFee = serviceFee;
	}



	public double getServiceFee() {
		return serviceFee;
	}



	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}



	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	@Override
	public String toString() {
		return "ServiceStatusDto [bookingId=" + bookingId + ", serviceStatus=" + serviceStatus + ", serviceFee="
				+ serviceFee + "]";
	}

}
