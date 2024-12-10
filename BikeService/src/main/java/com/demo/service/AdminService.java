package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dto.BikeWithCustDto;
import com.demo.dto.BookingStatusDto;
import com.demo.dto.EntityConvertor;
import com.demo.dto.LoginDto;
import com.demo.dto.LoginResponseDto;
import com.demo.dto.PaymentStatusDto;
import com.demo.dto.ProfileDto;
import com.demo.dto.ServiceStatusDto;
import com.demo.dto.UpdateBooking;
import com.demo.dto.UserDto;
import com.demo.dto.WalletTransactionDto;
import com.demo.entities.Bike;
import com.demo.entities.Booking;
import com.demo.entities.BookingStatus;
import com.demo.entities.PaymentStatus;
import com.demo.entities.ServiceStatus;
import com.demo.entities.User;
import com.demo.exception.UpdateException;
import com.demo.repository.BookingRepository;
import com.demo.repository.PaymentRepository;
import com.demo.repository.UserRepository;

import com.demo.repository.WalletRepo;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class AdminService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private WalletRepo walletRepo;
	
	@Autowired
	PaymentRepository paymentRepo;
	
//	public boolean verifyAdmin(LoginDto login) {
//		// TODO Auto-generated method stub
//		User u=userRepo.findByEmail(login.getEmail());
//		if((u.getPassword().equals(login.getPassword()))&&u.getRole().equals(login.getRole())) {
//			return true;
//		}
	
//		return false;
//	}
	
	public LoginResponseDto verifyAdmin(LoginDto ld) {
		// TODO Auto-generated method stub
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(ld.getEmail(), ld.getPassword()));
		if (authenticate.isAuthenticated()) {
			LoginResponseDto dt=new LoginResponseDto();
			User u=userRepo.findByEmail(ld.getEmail());
			dt.setId(u.getId());
			dt.setEmail(ld.getEmail());
			dt.setPassword(u.getPassword());
			dt.setRole(u.getRole());
			return dt;
		} else {
			return null;
		}

	}
	
	public List<UserDto> getCustomers() {
		// TODO Auto-generated method stub
		List<User>ls=userRepo.findByRole();
		List<UserDto>lst=ls.stream().map(e->EntityConvertor.entityToDto(e)).collect(Collectors.toList());
		return lst;
	}
	public List<BikeWithCustDto> getBikes() {
		// TODO Auto-generated method stub
		List<User>lst=userRepo.findAll();
		List<BikeWithCustDto> r=new ArrayList<>();
		for(User i:lst) {
			for(Bike b:i.getBikes()) {
				BikeWithCustDto d=new BikeWithCustDto();
				d.setFirstName(i.getFirstName());
				d.setLastName(i.getLastName());
				d.setMobileNumber(i.getMobileNumber());
				d.setCompany(b.getCompany());
				d.setModel(b.getModel());
				d.setRegistrationNumber(b.getRegistrationNumber());
				r.add(d);
			}
		}
		return r;
	}

	public List<ProfileDto> getAllCustomer() {
		// TODO Auto-generated method stub
		List<User>us=userRepo.findAll();
		List<ProfileDto>lst=new ArrayList<>();
		for(User i:us) {
			ProfileDto dt=new ProfileDto();
			dt.setFirstName(i.getFirstName());
			dt.setLastName(i.getLastName());
			dt.setEmail(i.getEmail());
			dt.setMobileNumber(i.getMobileNumber());
			dt.setGender(i.getGender());
			dt.setAddress(i.getAddress());
			lst.add(dt);
		}
		return lst;
	}

	public boolean updateServiceStatus(ServiceStatusDto sd) {
		// TODO Auto-generated method stub
//		ServiceStatus status;
		String status;
		if("Completed".equalsIgnoreCase(sd.getServiceStatus())) {
//			status=ServiceStatus.COMPLETED;
			status="Completed";
		}else if("Processing".equalsIgnoreCase(sd.getServiceStatus())) {
//			status=ServiceStatus.PROCESSING;
			status="Processing";
		}else {
//			status=ServiceStatus.WAITING;
			status="Waiting";
		}
		System.out.println("Fees:-"+sd.getServiceFee());
		int count=bookingRepo.updateServiceStatus(sd.getBookingId(),status,sd.getServiceFee());
		if(count>0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean updateBookingStatus(BookingStatusDto sd) {
		
		String status="";
		System.out.println("Booking status:= "+sd.getStatus());
		if("Approved".equalsIgnoreCase(sd.getStatus())) {
			System.out.println("Booking status:= "+sd.getStatus());
			status="Approved";
		}else if("Cancelled".equalsIgnoreCase(sd.getStatus())) {
			System.out.println("Booking status:= "+sd.getStatus());
			status="Cancelled";
		}else {
			status="Pending";
			System.out.println("Booking status:= "+sd.getStatus());
		}
		// TODO Auto-generated method stub
		int update=bookingRepo.updateBookingStatus(sd.getBookingId(),status,sd.getDate());
		System.out.println(sd.getDate());
		if(update>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean updatePaymentStatus(PaymentStatusDto sd) {
		String status="";
		String ps=paymentRepo.findPaymentStatusById(sd.getBookingId());
		System.out.println("Payment status:= "+sd.getStatus());
		
		if("Completed".equalsIgnoreCase(ps)) {
			return false;
		}
		
		if("Completed".equalsIgnoreCase(sd.getStatus())) {
			System.out.println("Booking status:= "+sd.getStatus());
			status="Completed";
		}else {
			status="Pending";
			System.out.println("Booking status:= "+sd.getStatus());
		}
		// TODO Auto-generated method stub
		int update=bookingRepo.updatePaymentStatus(sd.getBookingId(),status);
		if(update>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean updateBookings(UpdateBooking up) {
	    BookingStatus bookStatus = BookingStatus.PENDING;
	    ServiceStatus serviceStatus = ServiceStatus.WAITING;
	    PaymentStatus paymentStatus = PaymentStatus.PENDING;

	    // Update Booking Status
	    if ("Approved".equalsIgnoreCase(up.getBookingStatus())) {
	        bookStatus = BookingStatus.APPROVED;
	    } else if ("Cancelled".equalsIgnoreCase(up.getBookingStatus())) {
	        bookStatus = BookingStatus.CANCELLED;
	    }

	    // Update Service Status
	    if ("Completed".equalsIgnoreCase(up.getServiceStatus())) {
	        serviceStatus = ServiceStatus.COMPLETED;
	        System.out.println("service status:-"+serviceStatus);
	    } else if ("Processing".equalsIgnoreCase(up.getServiceStatus())) {
	        serviceStatus = ServiceStatus.PROCESSING;
	        System.out.println("service status:-"+serviceStatus);
	    }

	    
	    // Update Payment Status
//	    if ("Completed".equalsIgnoreCase(up.getPaymentStatus())) {
//	        paymentStatus = PaymentStatus.COMPLETED;
//	        System.out.println("Paymentstatus:-"+up.getPaymentStatus());
//	    }else {
//	    	paymentStatus = PaymentStatus.PENDING;
//	    	System.out.println("Paymentstatus:-"+paymentStatus);
//	    }
	    System.out.println("Service fee:-"+up.getServiceFee());
	    int updatedRows = bookingRepo.updateBooking(
	        up.getBookingId(),
	        up.getBookedDate(),
	        bookStatus,
	        serviceStatus,
	        paymentStatus,
	        up.getServiceFee()
	    );

	    return updatedRows > 0;
	}

	public Boolean withDrawMoney(WalletTransactionDto w) {
		
		Long uid=bookingRepo.findById(w.getBookingId()).get().getUser().getId();
		System.out.println("UserId:-"+uid);
		double amt=walletRepo.findAmountByUserId(uid);
		System.out.println("Wallet balance:-"+amt);
		String status=paymentRepo.findPaymentStatusById(w.getBookingId());
		String mode=paymentRepo.findPaymentModeByBookingId(w.getBookingId());
		
		System.out.println(mode);
		
		if(status.equalsIgnoreCase("Completed") || "CASH".equalsIgnoreCase(mode)) {
			System.out.println("Money cant withdraw as mode is cash");
			return false;
		}
		if(w.getAmount()>amt) {
			System.out.println(false);
			return false;
		}else {
			double rem=amt-w.getAmount();
			int count=walletRepo.remMoneyAfterDeduction(rem,uid);
			System.out.println("row affected:-"+count);
			if(count>0) {
				return true;
			}else {
				return false;
			}
		}
//		return true;
	}

	

	

	

	

/*	SELECT DISTINCT 
    bo.id AS booking_id,
    bi.company AS bike_company,
    bi.model AS bike_model,
    bi.registration_number AS bike_registration_number,
    CONCAT(u.first_name, ' ', u.last_name) AS full_name,
    bo.service_date,
    bo.booked_date,
    bo.booking_status,
    bo.service_status,
    bo.service_fee,
    p.payment_mode,
    p.payment_status
FROM 
    user u
JOIN 
   bike bi ON u.id = bi.user_id
JOIN 
    booking bo ON bi.id = bo.bike_id
JOIN 
    payment p ON bo.id = p.booking_id
WHERE 
    u.role = 'user';*/


}
