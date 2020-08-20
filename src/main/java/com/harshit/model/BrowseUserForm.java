package com.harshit.model;

import java.util.ArrayList;

public class BrowseUserForm {
	private String userType;
	private ArrayList<String> criteria;
	private ArrayList<String> selectedSkills;
	private String searchName;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ArrayList<String> getCriteria() {
		return criteria;
	}

	public void setCriteria(ArrayList<String> criteria) {
		this.criteria = criteria;
	}

	public ArrayList<String> getSelectedSkills() {
		return selectedSkills;
	}

	public void setSelectedSkills(ArrayList<String> selectedSkills) {
		this.selectedSkills = selectedSkills;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}