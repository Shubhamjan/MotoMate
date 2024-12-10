package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.BikeWithCustDto;
import com.demo.dto.BookingStatusDto;
import com.demo.dto.LoginDto;
import com.demo.dto.LoginResponseDto;
import com.demo.dto.PaymentStatusDto;
import com.demo.dto.ProfileDto;
import com.demo.dto.ServiceStatusDto;
import com.demo.dto.UpdateBooking;
import com.demo.dto.UserDto;
import com.demo.dto.WalletTransactionDto;
import com.demo.exception.UpdateException;
import com.demo.exception.UserNotFoundException;
import com.demo.service.AdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/login")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto login) {
		LoginResponseDto b = adminService.verifyAdmin(login);
		if (b != null) {
			return new ResponseEntity<LoginResponseDto>(b, HttpStatus.OK);
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDto>> getCustomers() {
		List<UserDto> lst = adminService.getCustomers();
		return new ResponseEntity<List<UserDto>>(lst, HttpStatus.ACCEPTED);
	}

	@GetMapping("/bikes")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<BikeWithCustDto>> getBikes() {
		List<BikeWithCustDto> lst = adminService.getBikes();
		return new ResponseEntity<List<BikeWithCustDto>>(lst, HttpStatus.ACCEPTED);
	}

//	@GetMapping("/getCustomer")
//	public ResponseEntity<List<ProfileDto>>getAllCustomers(){
//		List<ProfileDto>lst=adminService.getAllCustomer();
//		if(lst!=null) {
//			return new ResponseEntity<List<ProfileDto>>(lst,HttpStatus.ACCEPTED);
//		}else {
//			throw new UserNotFoundException("No User Found");
//		}
//	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateBooking(@RequestBody UpdateBooking up) {
		boolean flag = adminService.updateBookings(up);
		if (flag) {
			return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);
		} else {
			throw new UpdateException("Not updated");
		}
	}

	@PutMapping("/update/serviceStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateServiceStatus(@RequestBody ServiceStatusDto sd) {
		boolean flag = adminService.updateServiceStatus(sd);
		if (flag) {
			return new ResponseEntity<String>("Service Status Successfully", HttpStatus.OK);
		} else {
			throw new UpdateException("Failed to update");
		}
	}

	@PutMapping("/update/bookingStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateBookingStatus(@RequestBody BookingStatusDto sd) {
		boolean flag = adminService.updateBookingStatus(sd);
		if (flag) {
			return new ResponseEntity<String>("Booking Status Successfully", HttpStatus.OK);
		} else {
			throw new UpdateException("Failed to update");
		}
	}

	@PutMapping("/update/paymentStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updatePaymentStatus(@RequestBody PaymentStatusDto sd) {
		boolean flag = adminService.updatePaymentStatus(sd);
		if (flag) {
			return new ResponseEntity<String>("Payment Status Successfully", HttpStatus.OK);
		} else {
			throw new UpdateException("Failed to update");
		}
	}

	@PutMapping("/transaction")
@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> withdrawMoney(@RequestBody WalletTransactionDto w) {
		Boolean flag = adminService.withDrawMoney(w);
		if (flag) {
			return new ResponseEntity<String>("Money deducted successfully", HttpStatus.OK);
		} else {
			throw new RuntimeException("No money deducted.Wallet might have insufficient balance");
		}
	}

}
