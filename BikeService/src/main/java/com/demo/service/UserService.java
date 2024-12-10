package com.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dto.*;
import com.demo.entities.Bike;
import com.demo.entities.User;
import com.demo.entities.Wallet;
import com.demo.exception.UserNotFoundException;
import com.demo.repository.UserRepository;
import com.demo.repository.WalletRepo;

import jakarta.transaction.Transactional;

import com.demo.repository.BikeRepo;

@Transactional
@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BikeRepo bikeRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private WalletRepo walletRepo;

	public UserDto register(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bcrypt.encode(user.getPassword()));
		User u = userRepo.save(user);
		UserDto ut = EntityConvertor.entityToDto(u);
		System.out.println("In service layer:-"+ut);
		return ut;
	}

	public LoginResponseDto verify(LoginDto ld) {
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

	public BikeDto addBike(Bike b, Long id) {
		// TODO Auto-generated method stub
		User u = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		Bike bi=bikeRepo.findByRegistrationNumber(b.getRegistrationNumber());
		if(bi==null) {
			b.setUser(u);
			Bike bt = bikeRepo.save(b);

			BikeDto bd = EntityConvertor.BikeToDto(bt);
			return bd;
		}else {
			return null;
		}
		
	}

	public List<BikesDto> allBikes(Long id) {
		// TODO Auto-generated method stub
		List<Bike> bike = bikeRepo.findBikesByUserId(id);
		List<BikesDto> b = bike.stream().map(e -> EntityConvertor.BikesToDto(e)).collect(Collectors.toList());
		return b;
	}

	public ProfileDto getProfile(Long id) {
		// TODO Auto-generated method stub
		User u=userRepo.findById(id).get();
		if(u!=null) {
			ProfileDto d=new ProfileDto();
			d.setId(u.getId());
			d.setFirstName(u.getFirstName());
			d.setLastName(u.getLastName());
			d.setEmail(u.getEmail());
			d.setAddress(u.getAddress());
			d.setRole(u.getRole());
			d.setGender(u.getGender());
			d.setMobileNumber(u.getMobileNumber());
			return d;
		}
		return null;
	}

	public Long getUserId(String email) {
		// TODO Auto-generated method stub
		User u=userRepo.findByEmail(email);
		return u.getId();
	}

	public boolean updatePassword(ResetPassDto reset) {
		
		String user=userRepo.checkByEmail(reset.getEmail());
		System.out.println(user);
		if(user!=null) {
			System.out.println("with user object");
			reset.setNewPass(bcrypt.encode(reset.getNewPass()));
			int c=userRepo.updatePass(reset.getNewPass(),reset.getEmail());
			if(c>0) {
				return true;
			}else {
				return false;
			}
			
		}
		
		return false;
	}

}
