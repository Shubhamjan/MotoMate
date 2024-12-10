package com.demo.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.entities.PasswordResetToken;
import com.demo.entities.User;
import com.demo.exception.UserNotFoundException;
import com.demo.repository.PasswordResetTokenRepository;
import com.demo.repository.UserRepository;

@Service
public class PasswordResetService {

	@Autowired
	private PasswordResetTokenRepository tokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	public void createPasswordResetToken(String email) {

		User u = userRepository.findByEmail(email);
		if (u != null) {

			String token = UUID.randomUUID().toString();
			PasswordResetToken resetToken = new PasswordResetToken();
			resetToken.setToken(token);
			resetToken.setUser(u);
			resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));
			tokenRepository.save(resetToken);
			String resetLink = "http://localhost:8080/reset-password?token=" + token;
			emailService.sendEmail(u.getEmail(), "Reset Password",
					"Click the link to reset your password: " + resetLink);
		} else {
			throw new UserNotFoundException("User not found");
		}

	}

	public void resetPassword(String token, String newPass) {
		
		PasswordResetToken resetToken=tokenRepository.findByToken(token);
		
		if(resetToken!=null) {
			if(resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
				throw new RuntimeException("Token expired");
			}else {
				User user=resetToken.getUser();
				user.setPassword(new BCryptPasswordEncoder().encode(newPass));
				userRepository.save(user);
				tokenRepository.delete(resetToken);
			}
		}else {
			throw new RuntimeException("Invalid token");
		}

	}

}
