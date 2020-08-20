package com.harshit.model;

import java.util.ArrayList;

import com.harshit.Dao.Authority;

public class UpdateAccountForm {
	private String firstName;
	private String lastName;
	private String royalID;
	private String email;
	private String phoneNumber;
	private String address;
	private String password;
	private String confirmPassword;
	private ArrayList<String> roles;
	private ArrayList<Skill> skill;
	private ArrayList<JobHistory> workExperience;

	public UpdateAccountForm() {
		super();
		workExperience = new ArrayList<JobHistory>();
		skill = new ArrayList<Skill>();
		roles = new ArrayList<String>();
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}