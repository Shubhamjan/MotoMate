package com.demo.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.VerifyOtpDto;
import com.demo.entities.OtpVerification;
import com.demo.repository.OtpVerificationRepository;

@Service
public class OtpService {

	@Autowired
	private OtpVerificationRepository otpRepository;

	@Autowired
	private EmailService emailService;
	
	 private static final int OTP_VALID_DURATION = 5;

	public String generateOtp(String mail) {

		String otp = String.format("%06d", new Random().nextInt(999999));
		OtpVerification ot=new OtpVerification();
		ot.setEmail(mail);
		ot.setOtp(otp);
		ot.setExpirationTime(LocalDateTime.now().plusMinutes(OTP_VALID_DURATION));
		otpRepository.save(ot);
		
		emailService.sendEmail(mail, "Your OTP Code, Your OTP is: ", otp);
		return otp;
	}

	public boolean verifyOtp(VerifyOtpDto ot) {
		// TODO Auto-generated method stub
		var otpRecord = otpRepository.findByEmailAndOtp(ot.getEmail(),ot.getOtp());
		System.out.println(ot.getEmail()+" "+ot.getOtp());
		System.out.println(otpRecord);
		if(otpRecord.isPresent() && !otpRecord.get().getIsVerified()) {
			System.out.println("in 1st if");
			
			OtpVerification otVerify=otpRecord.get();
			
			if(otVerify.getExpirationTime().isBefore(LocalDateTime.now())) {
				System.out.println("in 2nd if");
				throw new RuntimeException("OTP is expired");
				
			}
			
			otVerify.setIsVerified(true);
			otpRepository.save(otVerify);
			
			return true;
		}
		return false;
	}

}
