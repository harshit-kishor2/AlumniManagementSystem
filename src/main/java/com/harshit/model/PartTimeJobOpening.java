package com.harshit.model;

import java.util.ArrayList;
import java.util.Date;

public class PartTimeJobOpening extends JobOpening {

	private String hoursPerWeek;

	public PartTimeJobOpening() {
		super();
	}

	public PartTimeJobOpening(int jobID, String position, String location, String proficiancy, Date startDate,
			String payrate, Company companyName, String jobDescription, ArrayList<Skill> skills, String hoursPerWeek) {
		super(jobID, position, location, proficiancy, startDate, payrate, companyName, jobDescription, skills);
		this.setHoursPerWeek(hoursPerWeek);
	}

	public String getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(String hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}
}