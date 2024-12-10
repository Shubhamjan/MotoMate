package com.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.entities.Booking;
import com.demo.exception.NoBookingFoundException;
import com.demo.repository.BookingRepository;

@Service
public class StatusService {

	@Autowired
	private BookingRepository bookingRepo;
	
	public String getCurrentBookingStatus(Long bid) {
		// TODO Auto-generated method stub
		String status=bookingRepo.findStatusById(bid);
		if(status!=null) {
			return status;
		}else {
			throw new NoBookingFoundException("No Booking for this id");
		}
		
	}

	public LocalDate getBookedDate(Long bid) {
		
		LocalDate d=bookingRepo.findBookedDate(bid);
		return d;
	}

}
