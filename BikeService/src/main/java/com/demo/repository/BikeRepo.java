package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Bike;

@Repository
public interface BikeRepo extends JpaRepository<Bike,Long>{

	@Query(value = "SELECT * FROM Bike b WHERE b.user_id = :id", nativeQuery = true)
	List<Bike> findBikesByUserId(@Param("id")Long id);

	Bike findByRegistrationNumber(String registrationId);

}
