package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {

	@Query(value="select * from wallet w where w.user_id=:uid ",nativeQuery=true)
	Wallet findByUserId(@Param("uid")Long id);

	@Modifying
	@Query(value="update wallet set balance =:bal where user_id=:id",nativeQuery=true)
	int updateWalletBalance(@Param("bal")double new_balance,@Param("id")long id);

	@Query(value="select balance from Wallet where user_id=:id ",nativeQuery=true)
	double findAmountByUserId(@Param("id")Long uid);

	
	@Modifying
	@Query(value="update wallet set balance=:rem where user_id=:id",nativeQuery=true)
	int remMoneyAfterDeduction(@Param("rem")double rem,@Param("id") Long uid);

}
