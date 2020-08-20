package com.harshit.model;

import java.time.LocalDate;

public class UserActivation {

	private String royalID;
	private String specialCode;
	private LocalDate date;

	public UserActivation(String royalID, String specialCode, LocalDate date) {
		super();
		this.royalID = royalID;
		this.specialCode = specialCode;
		this.date = date;
	}

	public UserActivation() {
		super();
		date = LocalDate.now();
	}

	public String getRoyalID() {
		return royalID;
	}

	public void setRoyalID(String royalID) {
		this.royalID = royalID;
	}

	public String getSpecialCode() {
		return specialCode;
	}

	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}