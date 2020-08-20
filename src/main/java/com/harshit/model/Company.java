package com.harshit.model;
/*
import java.util.ArrayList;

import edu.JIT.model.accountManagement.UserAccount;*/

public class Company {

	private String companyName;
	private String location;
	public String phone_Number;
	/*
	 * private String companyAddress; private ArrayList<JobOpening> jobOpenings;
	 * private ArrayList<UserAccount> alumni; private ArrayList<UserAccount>
	 * faculty; private ArrayList<UserAccount> currentStudents;
	 */

	public Company() {
		super();
	}

	public Company(String companyName, String location, String phoneNumber) {
		super();
		this.companyName = companyName;
		this.location = location;
		this.phone_Number = phoneNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phone_Number;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phone_Number = phoneNumber;
	}
}