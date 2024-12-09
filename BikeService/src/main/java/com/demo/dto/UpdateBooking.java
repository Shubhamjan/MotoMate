package com.demo.dto;

import java.time.LocalDate;

import com.demo.entities.BookingStatus;
import com.demo.entities.PaymentStatus;
import com.demo.entities.ServiceStatus;

public class UpdateBooking {

	private Long bookingId;
	
	private String registrationId;
	
	private LocalDate serviceDate;
	
	private LocalDate bookedDate;
	
	private String bookingStatus;
	
	private String serviceStatus;
	
	private  String paymentStatus;
	
	private double serviceFee;

	

	public UpdateBooking(Long bookingId, String registrationId, LocalDate serviceDate, LocalDate bookedDate,
			String bookingStatus, String serviceStatus, String paymentStatus, double serviceFee) {
		super();
		this.bookingId = bookingId;
		this.registrationId = registrationId;
		this.serviceDate = serviceDate;
		this.bookedDate = bookedDate;
		this.bookingStatus = bookingStatus;
		this.serviceStatus = serviceStatus;
		this.paymentStatus = paymentStatus;
		this.serviceFee = serviceFee;
	}

	public UpdateBooking() {
		super();
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public LocalDate getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(LocalDate serviceDate) {
		this.serviceDate = serviceDate;
	}

	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	@Override
	public String toString() {
		return "UpdateBooking [bookingId=" + bookingId + ", registrationId=" + registrationId + ", serviceDate="
				+ serviceDate + ", bookedDate=" + bookedDate + ", bookingStatus=" + bookingStatus + ", serviceStatus="
				+ serviceStatus + ", paymentStatus=" + paymentStatus + ", serviceFee=" + serviceFee + "]";
	}

	
	
}
