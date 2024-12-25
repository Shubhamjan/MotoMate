package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return security
//				.authorizeHttpRequests(auth->auth
//						.requestMatchers("/user/**").permitAll()
//						.anyRequest()
//						.authenticated())
////						.formLogin(form->form.permitAll())
//						.httpBasic(Customizer.withDefaults())
//						.build();
//		.requestMatchers("/user/**").hasRole("USER")
//		.requestMatchers("/home").permitAll()
//		.requestMatchers("/admin/**").hasRole("ADMIN")
		http
        .csrf().disable() // Disable CSRF for testing (or specifically for /user/register)
        .authorizeRequests()
            .requestMatchers("/admin/login").hasRole("ADMIN")  // Open registration endpoint
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/booking/getAll").hasRole("ADMIN")
            .requestMatchers("/user/**").hasRole("USER")
            .requestMatchers("/booking/bikebook").hasRole("USER")
            .requestMatchers("booking/cancel/**").hasRole("USER")
            .anyRequest().permitAll()  // Secure other endpoints
        .and();
//        .httpBasic(); // Use HTTP Basic Authentication for now

    return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(getDetailsService());
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception {
		return configuration.getAuthenticationManager();
		
	}
}
