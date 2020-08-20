package com.harshit.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class JobOpening {

	private int jobID;
	private String position;
	private String proficiancy;
	private String location;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	private String payrate;
	private Company companyName;
	private String jobDescription;
	private ArrayList<Skill> skills;

	public JobOpening() {
		super();
		skills = new ArrayList<Skill>();
	}

	public JobOpening(int jobID, String position, String proficiancy, String location, Date startDate, String payrate,
			Company companyName, String jobDescription, ArrayList<Skill> skills) {
		super();
		this.jobID = jobID;
		this.position = position;
		this.proficiancy = proficiancy;
		this.location = location;
		this.startDate = startDate;
		this.payrate = payrate;
		this.companyName = companyName;
		this.jobDescription = jobDescription;
		this.skills = skills;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getPayrate() {
		return payrate;
	}

	public void setPayrate(String payrate) {
		this.payrate = payrate;
	}

	public Company getCompanyName() {
		return companyName;
	}

	public void setCompanyName(Company companyName) {
		this.companyName = companyName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getProficiancy() {
		return proficiancy;
	}

	public void setProficiancy(String proficiancy) {
		this.proficiancy = proficiancy;
	}

	public void addSkills(Skill skill) {
		this.skills.add(skill);
	}
}