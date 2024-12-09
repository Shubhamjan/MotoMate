package com.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.AllBookingDto;
import com.demo.dto.UserBookings;
import com.demo.entities.Booking;
import com.demo.entities.BookingStatus;
import com.demo.entities.PaymentStatus;
import com.demo.entities.ServiceStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query(value = """
			SELECT
			    bo.id AS bookingId,
			    bi.model AS model,
			    bi.registration_number AS registrationNumber,
			    bi.company AS company,
			    bo.service_date AS serviceDate,
			    bo.booked_date AS bookedDate,
			    bo.booking_status AS bookingStatus,
			    bo.service_status AS serviceStatus,
			    bo.service_fee AS serviceFee,
			    p.payment_mode AS paymentMode,
			    p.payment_status AS paymentStatus
			FROM
			    Bike bi
			    INNER JOIN Booking bo ON bi.id = bo.bike_id
			    INNER JOIN Payment p ON bo.id = p.booking_id
			WHERE
			    p.user_id = :userId
			""", nativeQuery = true)
	List<Object[]> getBookings(@Param("userId") Long id);

	@Query(value = """
			    SELECT
			        bo.id AS bookingId,
			        bi.model AS model,
			        bi.registration_number AS registrationNumber,
			        bi.company AS company,
			        CONCAT(u.first_name, ' ', u.last_name) AS fullName,
			        u.mobile_number AS contactNumber,
			        bo.service_date AS serviceDate,
			        bo.booked_date AS bookedDate,
			        bo.booking_status AS bookingStatus,
			        bo.service_status AS serviceStatus,
			        bo.service_fee AS serviceFee,
			        p.payment_mode AS paymentMode,
			        p.payment_status AS paymentStatus
			    FROM user u
			    JOIN bike bi ON u.id = bi.user_id
			    JOIN booking bo ON bo.bike_id = bi.id
			    JOIN payment p ON bo.id = p.booking_id
			    WHERE u.role = 'user'order by u.id
			""", nativeQuery = true)
	List<Object[]> getAllBookings();
	
//			-----------------Testing Pagination----------------------


//	--------------------------------------------------------------------------------
	
	
	@Modifying
	@Query(value = """
	        UPDATE booking bo
	        JOIN payment p ON bo.id = p.booking_id
	        SET 
	            bo.booked_date = :bookedDate,
	            bo.booking_status = :bookStatus,
	            bo.service_status = :serviceStatus,
	            p.payment_status = :paymentStatus,
	            bo.service_fee = :serviceFee
	        WHERE bo.id = :bookingId
	    """, nativeQuery = true)
	int updateBooking(
	    @Param("bookingId") Long bookingId,
	    @Param("bookedDate") LocalDate bookedDate,
	    @Param("bookStatus") BookingStatus bookStatus,   // Use enum here
	    @Param("serviceStatus") ServiceStatus serviceStatus,  // Use enum here
	    @Param("paymentStatus") PaymentStatus paymentStatus,  // Use enum here
	    @Param("serviceFee") double serviceFee
	);

	@Modifying
	@Query(value="update booking b set b.service_status=:st,b.service_fee=:f where id=:bid",nativeQuery=true)
	int updateServiceStatus(@Param("bid")Long bookingId, @Param("st")String status,@Param("f")double fees);

	@Modifying
	@Query(value="update booking b set b.booking_status=:st,b.booked_date=:da where b.id=:bi",nativeQuery=true)
	int updateBookingStatus(@Param("bi")Long bookingId, @Param("st")String status,@Param("da")LocalDate date);

	@Modifying
	@Query(value="update booking b join payment p on b.id=p.booking_id set p.payment_status=:st where b.id=:bi",nativeQuery=true)
	int updatePaymentStatus(@Param("bi")Long bookingId, @Param("st")String status);

	@Query(value="select booking_status from booking where id=:id",nativeQuery=true)
	String findStatusById(@Param("id")Long bid);

	@Query(value="select booked_date from booking where id=:id",nativeQuery=true)
	LocalDate findBookedDate(@Param("id")Long bid);

	
	@Modifying
	@Query(value="update booking set booking_status=:state where id=:id",nativeQuery=true)
	int cancelBooking(@Param("state")String status,@Param("id") Long id);

	



//		@Modifying
//		@Query(value = "UPDATE booking bo JOIN payment p ON bo.id = p.booking_id "
//				+ "SET bo.booked_date = :bookDate, bo.booking_status = :bookStatus, "
//				+ "bo.service_status = :serviceStatus, p.payment_status = :paymentStatus, "
//				+ "bo.service_fee = :fee WHERE bo.id = :bid", nativeQuery = true)
//		int updateBooking(@Param("bid") Long bookingId,@Param("bookDate") LocalDate bookedDate,
//				@Param("bookStatus") BookingStatus bookingStatus,
//				@Param("serviceStatus") ServiceStatus serviceStatus,
//				@Param("paymentStatus") PaymentStatus paymentStatus,@Param("fee")double serviceFee);

}
