package com.harshit.model;

import java.util.ArrayList;

import com.harshit.Dao.Authority;

public class UserAccount {

	private String firstName;
	private String lastName;
	private String email;
	private String royalID;
	private String phoneNumber;
	private String userAddress;
	private ArrayList<String> roles;
	private ArrayList<String> company;
	private ArrayList<Skill> skill;
	private ArrayList<JobHistory> workExperience;
	private String specialCode;

	public UserAccount() {
		super();
		workExperience = new ArrayList<JobHistory>();
		skill = new ArrayList<Skill>();
		company = new ArrayList<String>();
		roles = new ArrayList<String>();
	}

	public UserAccount(String firstName, String lastName, String email, String royalID, String phoneNumber,
			ArrayList<String> roles, ArrayList<String> company, String userAddress, ArrayList<Skill> skill,
			ArrayList<JobHistory> workExperience) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.royalID = royalID;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
		this.company = company;
		this.userAddress = userAddress;
		this.skill = skill;
		this.workExperience = workExperience;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public ArrayList<String> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}

	public ArrayList<String> getCompany() {
		return company;
	}

	public void setCompany(ArrayList<String> company) {
		this.company = company;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public ArrayList<Skill> getSkill() {
		return skill;
	}

	public void setSkill(ArrayList<Skill> skill) {
		this.skill = skill;
	}

	public ArrayList<JobHistory> getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(ArrayList<JobHistory> workExperience) {
		this.workExperience = workExperience;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void addSkills(Skill skill) {
		this.skill.add(skill);
	}

	public void addWorkHistory(JobHistory job) {
		this.workExperience.add(job);
	}

	public void addRole(Authority authority) {
		this.roles.add(authority.getRole());
	}

	public void clearSkills() {
		this.skill.clear();
	}

	public String getSpecialCode() {
		return specialCode;
	}

	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}

	public void addRoles(String arrayList) {
		this.roles.add(arrayList);
	}
}