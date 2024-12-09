package com.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.dto.AllBookingDto;
import com.demo.dto.BookingBikeDto;
import com.demo.dto.EntityConvertor;
import com.demo.dto.UserBookings;
import com.demo.entities.Bike;
import com.demo.entities.Booking;
import com.demo.entities.BookingStatus;
import com.demo.entities.Payment;
import com.demo.entities.PaymentMode;
import com.demo.entities.PaymentStatus;
import com.demo.entities.ServiceStatus;
import com.demo.repository.BikeRepo;
import com.demo.repository.BookingRepository;
import com.demo.repository.PaymentRepository;
import com.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.demo.entities.User;

@Transactional
@Service
public class BikeService {
	
	@Autowired
	private BikeRepo bikeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	public BookingBikeDto bookBike(BookingBikeDto b) {
		// TODO Auto-generated method stub
		Bike bike=bikeRepo.findByRegistrationNumber(b.getRegistrationId());
		User u=userRepo.findById(b.getUid()).get();
		String mode="";
		if(bike!=null && u!=null) {
			Booking booking=new Booking();
			booking.setUser(u);
			booking.setBike(bike);
			booking.setServiceDate(b.getDate());
			booking.setBookedDate(b.getDate());
			booking.setBookingStatus(BookingStatus.PENDING);
			booking.setServiceFee(0.0);
//			booking.setPayment(p);
			booking.setServiceStatus(ServiceStatus.WAITING);
			bookingRepo.save(booking);
			
			Payment p=new Payment();
			if(b.getPaymentMode().equals("Cash")) {
				p.setPaymentMode(PaymentMode.CASH);
				mode="cash";
			}else {
				p.setPaymentMode(PaymentMode.Wallet);
				mode="Wallet";
			}
			p.setPaymentStatus(PaymentStatus.PENDING);
			p.setUser(u);
			p.setBooking(booking);
			paymentRepo.save(p);
			
			BookingBikeDto bBike=EntityConvertor.BookingToBookingBikeDto(booking,mode);
			return bBike;
		}
		return null;
	}

	public List<UserBookings> getBookings(Long id) {
		// TODO Auto-generated method stub
		List<Object[]> bk= bookingRepo.getBookings(id);
		List<UserBookings>lst=new ArrayList<>();
		for(Object[]i:bk) {
			UserBookings ub=new UserBookings(
					((Number)i[0]).longValue(),
					(String)i[1],
					(String)i[2],
					(String)i[3],
					((Date)i[4]).toLocalDate(),
					((Date)i[5]).toLocalDate(),
					BookingStatus.valueOf((String) i[6]),
					ServiceStatus.valueOf((String)i[7]),
					((Number) i[8]).doubleValue(),
					PaymentMode.valueOf((String)i[9]),
					PaymentStatus.valueOf((String) i[10])
					);
			lst.add(ub);
		};
		return lst;
	}

	public List<AllBookingDto> getAllBookings() {
		// TODO Auto-generated method stub
		List<Object[]>lst =bookingRepo.getAllBookings();
		List<AllBookingDto> bookings = new ArrayList<>();
		for (Object[] result : lst) {
            AllBookingDto dto = new AllBookingDto();
            dto.setBookingId(((Number) result[0]).longValue()); // Cast Number to Long
            dto.setModel((String) result[1]);
            dto.setRegistrationNumber((String) result[2]);
            dto.setCompany((String) result[3]);
            dto.setFullName((String) result[4]);
            dto.setContactNumber((String) result[5]);
            dto.setServiceDate(((java.sql.Date) result[6]).toLocalDate());
            dto.setBookedDate(((java.sql.Date) result[7]).toLocalDate());
            dto.setBookingStatus(BookingStatus.valueOf((String) result[8])); // Assuming enum is stored as String
            dto.setServiceStatus(ServiceStatus.valueOf((String) result[9]));
            dto.setServiceFee(((Number) result[10]).doubleValue());
            dto.setPaymentMode(PaymentMode.valueOf((String) result[11]));
            dto.setPaymentStatus(PaymentStatus.valueOf((String) result[12]));

            bookings.add(dto);
        }
		 bookings.sort(Comparator.comparing(AllBookingDto::getBookingId).reversed());

        return bookings;
	}
//			---------------testing pagination-------------------

	public boolean cancelBooking(String status, Long id) {
		// TODO Auto-generated method stub
		
		Booking b=bookingRepo.findById(id).get();
		
		if(b!=null) {
			boolean sStatus=("COMPLETED".equalsIgnoreCase(b.getServiceStatus().name())) || 
					("Processing".equalsIgnoreCase(b.getServiceStatus().name()) ||
							("COMPLETED".equalsIgnoreCase(b.getPayment().getPaymentStatus().name())));
			if(sStatus) {
				return false;
			}
			int c=bookingRepo.cancelBooking(status,id);
			if(c>0) {
				return true;
			}
		}
		return false;
	}
	
	

}
