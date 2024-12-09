package com.demo.dto;

public class ResetPasswordDto {
	
	private String token;
	
	private String newPass;

	public ResetPasswordDto() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Override
	public String toString() {
		return "resetPasswordDto [token=" + token + ", newPass=" + newPass + "]";
	}
	
}
