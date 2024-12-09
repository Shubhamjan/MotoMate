package com.demo.dto;

import com.demo.entities.Bike;
import com.demo.entities.Booking;
import com.demo.entities.User;
import com.demo.entities.Wallet;

public class EntityConvertor {

	public static UserDto entityToDto(User u) {
		UserDto ud=new UserDto();
		ud.setId(u.getId());
		ud.setEmail(u.getEmail());
		ud.setPassword(u.getPassword());
		ud.setRole(u.getRole());
		ud.setAddress(u.getAddress());
		ud.setMobileNumber(u.getMobileNumber());
		ud.setFirstName(u.getFirstName());
		ud.setLastName(u.getLastName());
		ud.setGender(u.getGender());
		return ud;
	}

	public static BikeDto BikeToDto(Bike bt) {
		// TODO Auto-generated method stub
		BikeDto br=new BikeDto();
		br.setCompany(bt.getCompany());
		br.setId(bt.getId());
		br.setModel(bt.getModel());
		br.setRegistrationNumber(bt.getRegistrationNumber());
//		br.setUser(bt.getUser());
		return br;
	}
	public static BikesDto BikesToDto(Bike bt) {
		// TODO Auto-generated method stub
		BikesDto br=new BikesDto();
		br.setCompany(bt.getCompany());
		br.setId(bt.getId());
		br.setModel(bt.getModel());
		br.setRegistrationNumber(bt.getRegistrationNumber());
		return br;
	}
	
	public static BookingBikeDto BookingToBookingBikeDto(Booking b,String mode) {
		BookingBikeDto bt=new BookingBikeDto();
		bt.setDate(b.getServiceDate());
		bt.setRegistrationId(b.getBike().getRegistrationNumber());
		bt.setUid(b.getUser().getId());
//		String str=b.getPayment().getPaymentMode().name().substring(0)+b.getPayment().getPaymentMode().name().substring(1).toLowerCase();
		bt.setPaymentMode(mode);
		return bt;
	}

	public static WalletDto walletToDto(Wallet w) {
		// TODO Auto-generated method stub
		WalletDto wt=new WalletDto();
		wt.setId(w.getId());
		wt.setFirstName(w.getUser().getFirstName());
		wt.setLastName(w.getUser().getLastName());
		wt.setNumber(w.getUser().getMobileNumber());
		wt.setBalance(w.getBalance());
		return wt;
	}
}
