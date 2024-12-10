package com.demo.dto;

import java.time.LocalDate;

public class BookingBikeDto {
	
	private Long uid;
	
	private String registrationId;
	
	private LocalDate date;
	
	private String paymentMode;

	public BookingBikeDto() {
		super();
	}

	public BookingBikeDto(Long uid, String registrationId, LocalDate date, String paymentMode) {
		super();
		this.uid = uid;
		this.registrationId = registrationId;
		this.date = date;
		this.paymentMode = paymentMode;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getRegistrationId() {
		return registrationId;
	}



	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}



	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BookingBikeDto [uid=" + uid + ", registrationId=" + registrationId + ", date=" + date + ", paymentMode="
				+ paymentMode + "]";
	}
}
