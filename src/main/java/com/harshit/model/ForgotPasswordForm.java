package com.harshit.model;

public class ForgotPasswordForm {

	private String email;
	private String royalID;
	private String password;
	private String confirmPassword;
	
	
	public ForgotPasswordForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ForgotPasswordForm(String email, String royalID, String password,String confirmPassword) {
		super();
		this.email = email;
		this.royalID = royalID;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoyalID() {
		return royalID;
	}
	public void setRoyalID(String royalID) {
		this.royalID = royalID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
