package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{

	@Query(value="select payment_status from payment where booking_id=:id",nativeQuery=true)
	String findPaymentStatusById(@Param("id")Long bookingId);

	@Query(value="select payment_mode from payment where booking_id=:id",nativeQuery=true)
	String findPaymentModeByBookingId(@Param("id")Long bookingId);

}
