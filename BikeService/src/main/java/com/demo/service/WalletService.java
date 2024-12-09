package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.EntityConvertor;
import com.demo.dto.WalletDto;
import com.demo.entities.User;
import com.demo.entities.Wallet;
import com.demo.repository.UserRepository;
import com.demo.repository.WalletRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WalletService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private WalletRepo walletRepo;
	public boolean addMoneyToWallet(Long id, double bal) {
		// TODO Auto-generated method stub
		User u=userRepo.findById(id).get();
		if(u.getWallet()==null) {
			Wallet w=new Wallet();
			w.setUser(u);
			walletRepo.save(w);
		} 
		Wallet wt=walletRepo.findByUserId(id);
		double new_balance=bal+wt.getBalance();
		int r=walletRepo.updateWalletBalance(new_balance,id);
		if(r>0) {
			return true;
		}else {
			return false;
		}
	}

	public WalletDto getMyWallet(Long id) {
//		User u=userRepo.findById(id).get();
//		System.out.println(u.getFirstName());
//		Wallet w=u.getWallet();
//		System.out.println(w.getBalance());
//		WalletDto wd=EntityConvertor.walletToDto(w);
		Wallet w=walletRepo.findByUserId(id);
		WalletDto wd=EntityConvertor.walletToDto(w);
		return wd;
	}

}
