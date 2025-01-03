package com.demo.dto;

public class PaymentStatusDto {
	
	private Long bookingId;
	
	private String status;

	public PaymentStatusDto() {
		super();
	}

	public PaymentStatusDto(Long bookingId, String status) {
		super();
		this.bookingId = bookingId;
		this.status = status;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PaymentStatusDto [bookingId=" + bookingId + ", status=" + status + "]";
	}
	
}
