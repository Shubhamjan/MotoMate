package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Bike;
import com.demo.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

	
	User findByEmail(String email);

	@Query("select u from User u where u.role='USER'")
	List<User> findByRole();

	@Query(value = "SELECT email FROM user WHERE email = :email", nativeQuery = true)
	String checkByEmail(@Param("email")String email);

	@Modifying
	@Query(value="update user set password=:pass where email=:mail",nativeQuery=true)
	int updatePass(@Param("pass")String newPass, @Param("mail")String email);
	
	

	
}
