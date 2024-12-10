package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.BikeDto;
import com.demo.dto.BikesDto;
import com.demo.dto.LoginDto;
import com.demo.dto.LoginResponseDto;
import com.demo.dto.ProfileDto;
import com.demo.dto.UserBookings;
import com.demo.dto.UserDto;
import com.demo.dto.WalletDto;
import com.demo.entities.Bike;
import com.demo.entities.User;
import com.demo.exception.BikeCheckException;
import com.demo.exception.UserNotFoundException;
import com.demo.exception.WalletNotFoundException;
import com.demo.service.UserService;
import com.demo.service.WalletService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	WalletService walletService;

	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<UserDto> register(@RequestBody User user) {
		System.out.println(user);
		UserDto u = userService.register(user);
		return new ResponseEntity<UserDto>(u, HttpStatus.OK);
	}

	@PostMapping("/login")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto ld) {
		LoginResponseDto b = userService.verify(ld);
		if (b != null) {
			return new ResponseEntity<LoginResponseDto>(b, HttpStatus.OK);
		} else {
			throw new UserNotFoundException("Not found");
		}
	}

	@PostMapping("/addBike/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<BikeDto> addBike(@RequestBody Bike b, @PathVariable Long id) {
		BikeDto bt = userService.addBike(b, id);
		if (bt != null) {
			return new ResponseEntity<BikeDto>(bt, HttpStatus.OK);
		} else {
			throw new BikeCheckException("Cannot add same bike one more time");
		}

	}

	@GetMapping("/bikes/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<BikesDto>> allBikes(@PathVariable Long id) {
		List<BikesDto> bikes = userService.allBikes(id);
		return new ResponseEntity<List<BikesDto>>(bikes, HttpStatus.ACCEPTED);
	}

	@PostMapping("/addMoney/{id}/{money}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> addMoneyToWallet(@PathVariable Long id, @PathVariable double money) {
		boolean flag = walletService.addMoneyToWallet(id, money);
		if (flag) {
			return new ResponseEntity<String>("Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed to add", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/getWallet/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<WalletDto> getMyWallet(@PathVariable Long id) {
		try {
			WalletDto w = walletService.getMyWallet(id);
			return new ResponseEntity<WalletDto>(w, HttpStatus.OK);
		} catch (Exception e) {
			throw new WalletNotFoundException("Wallet not found");
		}
	}

	@GetMapping("/getProfile/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
		ProfileDto dt = userService.getProfile(id);
		if (dt != null) {
			return new ResponseEntity<ProfileDto>(dt, HttpStatus.ACCEPTED);
		} else {
			throw new UserNotFoundException("User not found");
		}

	}

	@GetMapping("/getId/{email}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Long> getUserId(@PathVariable String email) {
		Long id = userService.getUserId(email);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

}
