package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ResetPassDto;
import com.demo.dto.ResetPasswordDto;
import com.demo.dto.VerifyOtpDto;
import com.demo.service.OtpService;
import com.demo.service.PasswordResetService;
import com.demo.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class PasswordResetController {
	
//	@Autowired
//	private PasswordResetService resetService;
//	
//	@PostMapping("/forget/{email}")
//	public ResponseEntity<String>forgetPassword(@PathVariable String email){
//		
//		resetService.createPasswordResetToken(email);
//		return new ResponseEntity<String>("Password reset link sent to you email",HttpStatus.OK);
//	}
//	
//	@PostMapping("/reset")
//	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto reset){
//		resetService.resetPassword(reset.getToken(),reset.getNewPass());
//		return new ResponseEntity<String>("Password has been reset successfully",HttpStatus.OK);
//	}
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/send-otp/{mail}")
	public ResponseEntity<String> sentOtp(@PathVariable String mail){
		String otp=otpService.generateOtp(mail);
		if(otp!=null) {
			return new ResponseEntity<String>("OTP Sent Successfully",HttpStatus.OK);
		}else {
			throw new RuntimeException("OTP not sent");
		}
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<?>verifyOtp(@RequestBody VerifyOtpDto ot){
		
		boolean isValid=otpService.verifyOtp(ot);
		
		if(isValid) {
			return new ResponseEntity<>("Otp verified successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Otp not verified successfully",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPassDto reset){
		
		System.out.println("from frontend:-"+reset);
		boolean flag= userService.updatePassword(reset);
		
		if(flag) {
			return new ResponseEntity<String>("Password reset successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Password reset not successfully",HttpStatus.EXPECTATION_FAILED);
		}
	}

}
