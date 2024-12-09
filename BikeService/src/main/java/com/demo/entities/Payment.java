package com.demo.entities;

import jakarta.persistence.*;


@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @OneToOne
    @JoinColumn(name="booking_id")
    private Booking booking;
    // Getters and Setters

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getPaymentId() {
        return paymentId;
    }

    public Payment() {
		super();
	}

	

	public Payment(Long paymentId, PaymentMode paymentMode, PaymentStatus paymentStatus, Booking booking, User user) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
		this.booking = booking;
		this.user = user;
	}

	public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", paymentStatus=" + paymentStatus
				+ ", booking=" + booking + ", user=" + user + "]";
	}

	
}
