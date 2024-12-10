package com.demo.dto;

import java.time.LocalDate;

public class BookingStatusDto {

	private Long bookingId;

	private String status;

	private LocalDate date;

	public BookingStatusDto() {
		super();
	}

	public BookingStatusDto(Long bookingId, String status, LocalDate date) {
		super();
		this.bookingId = bookingId;
		this.status = status;
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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
		return "BookingStatusDto [bookingId=" + bookingId + ", status=" + status + ", date=" + date + "]";
	}

	

}
