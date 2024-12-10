package com.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_verification")
public class OtpVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private LocalDateTime expirationTime;

    @Column(nullable = false)
    private Boolean isVerified = false;

	public OtpVerification() {
		super();
	}

	public OtpVerification(Long id, String email, String otp, LocalDateTime expirationTime, Boolean isVerified) {
		super();
		this.id = id;
		this.email = email;
		this.otp = otp;
		this.expirationTime = expirationTime;
		this.isVerified = isVerified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	@Override
	public String toString() {
		return "OtpVerification [id=" + id + ", email=" + email + ", otp=" + otp + ", expirationTime=" + expirationTime
				+ ", isVerified=" + isVerified + "]";
	}

 
}
