package com.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
	@SequenceGenerator(name = "booking_seq", sequenceName = "booking_sequence", initialValue = 1001, allocationSize = 1)
	private Long id;

	private LocalDate serviceDate;

	private LocalDate bookedDate;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus; // PENDING, APPROVED, CANCELLED

	@Enumerated(EnumType.STRING)
	private ServiceStatus serviceStatus; // WAITING, PROCESSING, COMPLETED

	private Double serviceFee;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "bike_id")
	private Bike bike;

	@OneToOne(mappedBy="booking")
	private Payment payment; 
	
	public Booking() {
		super();
	}

	public Booking(Long id, LocalDate serviceDate, LocalDate bookedDate, BookingStatus bookingStatus,
			ServiceStatus serviceStatus, Double serviceFee, User user, Bike bike, Payment payment) {
		super();
		this.id = id;
		this.serviceDate = serviceDate;
		this.bookedDate = bookedDate;
		this.bookingStatus = bookingStatus;
		this.serviceStatus = serviceStatus;
		this.serviceFee = serviceFee;
		this.user = user;
		this.bike = bike;
		this.payment = payment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", serviceDate=" + serviceDate + ", bookedDate=" + bookedDate + ", bookingStatus="
				+ bookingStatus + ", serviceStatus=" + serviceStatus + ", serviceFee=" + serviceFee + ", user=" + user
				+ ", bike=" + bike + ", payment=" + payment + "]";
	}

	
}
