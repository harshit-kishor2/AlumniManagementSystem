package com.harshit.model;

import java.util.ArrayList;

public class SortJobOpening {
	private InternshipJobOpening jobOpening;
	private ArrayList<String> positions;
	private ArrayList<String> locations;
	private ArrayList<String> hoursPerWeek;
	private ArrayList<String> companyName;
	private ArrayList<String> proficiency;

	public SortJobOpening() {
		super();
		jobOpening = new InternshipJobOpening();
		positions = new ArrayList<String>();
		locations = new ArrayList<String>();
		hoursPerWeek = new ArrayList<String>();
		companyName = new ArrayList<String>();
		proficiency = new ArrayList<String>();
	}

	public ArrayList<String> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<String> positions) {
		this.positions = positions;
	}

	public ArrayList<String> getlocations() {
		return locations;
	}

	public void setlocations(ArrayList<String> locations) {
		this.locations = locations;
	}

	public ArrayList<String> getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(ArrayList<String> hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public ArrayList<String> getCompanyName() {
		return companyName;
	}

	public void setCompanyName(ArrayList<String> companyName) {
		this.companyName = companyName;
	}

	public ArrayList<String> getProficiency() {
		return proficiency;
	}

	public void setProficiency(ArrayList<String> proficiency) {
		this.proficiency = proficiency;
	}

	public InternshipJobOpening getJobOpening() {
		return jobOpening;
	}

	public void setJobOpening(InternshipJobOpening jobOpening) {
		this.jobOpening = jobOpening;
	}

	public void addCompanyName(String companyName) {
		this.companyName.add(companyName);
	}

	public void addProficency(String proficiency) {
		this.proficiency.add(proficiency);
	}

	public void addHoursPerWeek(String hoursPerWeek) {
		this.hoursPerWeek.add(hoursPerWeek);
	}

	public void addLocation(String location) {
		this.locations.add(location);
	}

	public void addPosition(String position) {
		this.positions.add(position);
	}
}