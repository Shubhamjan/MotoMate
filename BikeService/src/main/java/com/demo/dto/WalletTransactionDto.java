package com.demo.dto;

public class WalletTransactionDto {

	private Long bookingId;
	
	private double amount;

	public WalletTransactionDto() {
		super();
	}

	public WalletTransactionDto(Long bookingId, double amount) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "WalletTransactionDto [bookingId=" + bookingId + ", amount=" + amount + "]";
	}
	
	
}
