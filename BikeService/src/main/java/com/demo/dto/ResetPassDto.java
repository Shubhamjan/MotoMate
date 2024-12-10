package com.demo.dto;

public class ResetPassDto {

	private String email;
	
	private String newPass;

	public ResetPassDto() {
		super();
	}

	public ResetPassDto(String email, String newPass) {
		super();
		this.email = email;
		this.newPass = newPass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Override
	public String toString() {
		return "ResetPassDto [email=" + email + ", newPass=" + newPass + "]";
	}
	
	
}
