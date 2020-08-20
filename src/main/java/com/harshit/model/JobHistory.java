package com.harshit.model;

public class JobHistory {

	private String rid;
	private String companyName;
	private String position;
	private String address;
	private String startDate;
	private String endDate;
	private boolean currentlyEmployed;

	public JobHistory(String companyName, String position, String address, String startDate, String endDate,
			boolean currentlyEmployed) {
		super();
		this.companyName = companyName;
		this.position = position;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.currentlyEmployed = currentlyEmployed;
	}

	public JobHistory() {
		super();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isCurrentlyEmployed() {
		return currentlyEmployed;
	}

	public void setCurrentlyEmployed(Boolean currentlyEmployed) {
		this.currentlyEmployed = currentlyEmployed;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}
}