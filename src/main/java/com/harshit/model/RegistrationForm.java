package com.harshit.model;

import java.util.Random;

public class RegistrationForm {

	private UserAccount account;
	private String password;
	private String confirmPassword;
	private String specialCode;

	public RegistrationForm() {
		super();
		this.account = new UserAccount();
	}

	public RegistrationForm(UserAccount account, String password, String confirmPassword, String specialCode) {
		super();
		this.account = account;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.specialCode = specialCode;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSpecialCode() {
		return specialCode;
	}

	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}

	public String createSpecialCode() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		specialCode = buffer.toString();
		return specialCode;
	}
}