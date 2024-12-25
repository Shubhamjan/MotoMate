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



	public LoginResponseDto verifyAdmin(LoginDto ld) {

		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(ld.getEmail(), ld.getPassword()));
		if (authenticate.isAuthenticated()) {
			LoginResponseDto dt = new LoginResponseDto();
			User u = userRepo.findByEmail(ld.getEmail());
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

		List<User> ls = userRepo.findByRole();
		List<UserDto> lst = ls.stream().map(e -> EntityConvertor.entityToDto(e)).collect(Collectors.toList());
		return lst;
	}

	public List<BikeWithCustDto> getBikes() {

		List<User> lst = userRepo.findAll();
		List<BikeWithCustDto> r = new ArrayList<>();
		for (User i : lst) {
			for (Bike b : i.getBikes()) {
				BikeWithCustDto d = new BikeWithCustDto();
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

		List<User> us = userRepo.findAll();
		List<ProfileDto> lst = new ArrayList<>();
		for (User i : us) {
			ProfileDto dt = new ProfileDto();
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
		
		String status;
		if ("Completed".equalsIgnoreCase(sd.getServiceStatus())) {

			status = "Completed";
		} else if ("Processing".equalsIgnoreCase(sd.getServiceStatus())) {

			status = "Processing";
		} else {

			status = "Waiting";
		}
		System.out.println("Fees:-" + sd.getServiceFee());
		int count = bookingRepo.updateServiceStatus(sd.getBookingId(), status, sd.getServiceFee());
		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean updateBookingStatus(BookingStatusDto sd) {

		String status = "";
		System.out.println("Booking status:= " + sd.getStatus());
		if ("Approved".equalsIgnoreCase(sd.getStatus())) {
			System.out.println("Booking status:= " + sd.getStatus());
			status = "Approved";
		} else if ("Cancelled".equalsIgnoreCase(sd.getStatus())) {
			System.out.println("Booking status:= " + sd.getStatus());
			status = "Cancelled";
		} else {
			status = "Pending";
			System.out.println("Booking status:= " + sd.getStatus());
		}

		int update = bookingRepo.updateBookingStatus(sd.getBookingId(), status, sd.getDate());
		System.out.println(sd.getDate());
		if (update > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updatePaymentStatus(PaymentStatusDto sd) {
		String status = "";
		String ps = paymentRepo.findPaymentStatusById(sd.getBookingId());
		System.out.println("Payment status:= " + sd.getStatus());

		if ("Completed".equalsIgnoreCase(ps)) {
			return false;
		}

		if ("Completed".equalsIgnoreCase(sd.getStatus())) {
			System.out.println("Booking status:= " + sd.getStatus());
			status = "Completed";
		} else {
			status = "Pending";
			System.out.println("Booking status:= " + sd.getStatus());
		}
		
		int update = bookingRepo.updatePaymentStatus(sd.getBookingId(), status);
		if (update > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateBookings(UpdateBooking up) {
		BookingStatus bookStatus = BookingStatus.PENDING;
		ServiceStatus serviceStatus = ServiceStatus.WAITING;
		PaymentStatus paymentStatus = PaymentStatus.PENDING;

		if ("Approved".equalsIgnoreCase(up.getBookingStatus())) {
			bookStatus = BookingStatus.APPROVED;
		} else if ("Cancelled".equalsIgnoreCase(up.getBookingStatus())) {
			bookStatus = BookingStatus.CANCELLED;
		}

	
		if ("Completed".equalsIgnoreCase(up.getServiceStatus())) {
			serviceStatus = ServiceStatus.COMPLETED;
			System.out.println("service status:-" + serviceStatus);
		} else if ("Processing".equalsIgnoreCase(up.getServiceStatus())) {
			serviceStatus = ServiceStatus.PROCESSING;
			System.out.println("service status:-" + serviceStatus);
		}



		System.out.println("Service fee:-" + up.getServiceFee());
		int updatedRows = bookingRepo.updateBooking(up.getBookingId(), up.getBookedDate(), bookStatus, serviceStatus,
				paymentStatus, up.getServiceFee());

		return updatedRows > 0;
	}

	public Boolean withDrawMoney(WalletTransactionDto w) {

		Long uid = bookingRepo.findById(w.getBookingId()).get().getUser().getId();
		System.out.println("UserId:-" + uid);
		double amt = walletRepo.findAmountByUserId(uid);
		System.out.println("Wallet balance:-" + amt);
		String status = paymentRepo.findPaymentStatusById(w.getBookingId());
		String mode = paymentRepo.findPaymentModeByBookingId(w.getBookingId());
		String serviceStatus = bookingRepo.findById(w.getBookingId()).get().getServiceStatus().name();
		System.out.println(mode);

		if (status.equalsIgnoreCase("Completed") || "CASH".equalsIgnoreCase(mode)) {
			System.out.println("Money cant withdraw as mode is cash");
			return false;
		}
		if (w.getAmount() > amt) {
			System.out.println(false);
			return false;
		}
		if (serviceStatus.equalsIgnoreCase("Completed")) {
			double rem = amt - w.getAmount();
			int count = walletRepo.remMoneyAfterDeduction(rem, uid);
			System.out.println("row affected:-" + count);
			if (count > 0) {
				int co = bookingRepo.updatePaymentStatus(w.getBookingId(), "Completed");
				System.out.println("row affected for payment status " + co);
				if (co > 0) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} else {
			System.out.println("in last else");
			return false;
		}

	}

}
