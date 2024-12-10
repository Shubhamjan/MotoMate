package com.demo.dto;

import java.time.LocalDate;

import com.demo.entities.BookingStatus;
import com.demo.entities.PaymentMode;
import com.demo.entities.PaymentStatus;
import com.demo.entities.ServiceStatus;


public class UserBookings {
	
	private Long bookingId;
	
	private String model;
	private String registrationNumber;
	private String company;
	private LocalDate serviceDate;

	private LocalDate bookedDate;
	
	private BookingStatus bookingStatus; // PENDING, APPROVED, CANCELLED


	private ServiceStatus serviceStatus; // WAITING, PROCESSING, COMPLETED

	private Double serviceFee;
	
	private PaymentMode paymentMode;

    private PaymentStatus paymentStatus;

	public UserBookings() {
		super();
	}

	public UserBookings(Long bookingId, String model, String registrationNumber, String company, LocalDate serviceDate,
			LocalDate bookedDate, BookingStatus bookingStatus, ServiceStatus serviceStatus, Double serviceFee,
			PaymentMode paymentMode, PaymentStatus paymentStatus) {
		super();
		this.bookingId = bookingId;
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.company = company;
		this.serviceDate = serviceDate;
		this.bookedDate = bookedDate;
		this.bookingStatus = bookingStatus;
		this.serviceStatus = serviceStatus;
		this.serviceFee = serviceFee;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "UserBookings [bookingId=" + bookingId + ", model=" + model + ", registrationNumber="
				+ registrationNumber + ", company=" + company + ", serviceDate=" + serviceDate + ", bookedDate="
				+ bookedDate + ", bookingStatus=" + bookingStatus + ", serviceStatus=" + serviceStatus + ", serviceFee="
				+ serviceFee + ", paymentMode=" + paymentMode + ", paymentStatus=" + paymentStatus + "]";
	}
}
