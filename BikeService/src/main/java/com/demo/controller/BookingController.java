package com.demo.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AllBookingDto;
import com.demo.dto.BookingBikeDto;
import com.demo.dto.UserBookings;
import com.demo.exception.NoBookingFoundException;
import com.demo.service.BikeService;
import com.demo.service.StatusService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

	@Autowired
	private BikeService bikeService;

	@Autowired
	private StatusService service;

	@PostMapping("/bikebook")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<BookingBikeDto> bookService(@RequestBody BookingBikeDto b) {
		BookingBikeDto bt = bikeService.bookBike(b);
		return new ResponseEntity<BookingBikeDto>(bt, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<UserBookings>> getBookings(@PathVariable Long id) {
		List<UserBookings> ub = bikeService.getBookings(id);
		if (ub != null) {
			 ub.sort(Comparator.comparing(UserBookings::getBookingId).reversed());
			return new ResponseEntity<List<UserBookings>>(ub, HttpStatus.ACCEPTED);
		} else {
			throw new NoBookingFoundException("No Booking found for this user");
		}
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AllBookingDto>> getAllBookings() {
		List<AllBookingDto> ub = bikeService.getAllBookings();
		
		if (ub != null) {
			 ub.sort(Comparator.comparing(AllBookingDto::getBookingId).reversed());
			return new ResponseEntity<List<AllBookingDto>>(ub, HttpStatus.ACCEPTED);
		} else {
			throw new NoBookingFoundException("No Booking found for this user");
		}
	}
//				--------------Test for pagination---------------------
	
	
	
	//	----------------------------------------------------------------------------------
	
	
	
	@GetMapping("/get/status/{bid}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> getCurrentBookingStatus(@PathVariable Long bid) {
		String status = service.getCurrentBookingStatus(bid);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@GetMapping("/get/date/{bid}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<LocalDate> getBookedDate(@PathVariable Long bid) {
		LocalDate d = service.getBookedDate(bid);
		if (d != null) {
			return new ResponseEntity<LocalDate>(d, HttpStatus.OK);
		} else {
			throw new NoBookingFoundException("No Booking found for this user");
		}
	}
	
	@PutMapping("/cancel/{status}/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> cancelooking(@PathVariable String status,@PathVariable Long id){
		boolean flag=bikeService.cancelBooking(status,id);
		System.out.println(status+"  "+id);
		if (flag) {
			return new ResponseEntity<String>("Cancelled the Booking", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(" Booking not cancelled",HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
