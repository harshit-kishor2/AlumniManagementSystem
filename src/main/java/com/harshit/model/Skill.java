package com.harshit.model;

public class Skill {

	private int skillID;
	private String proficiency;
	private String skillName;

	public Skill(int skillID, String proficiency, String skillName) {
		super();
		this.skillID = skillID;
		this.proficiency = proficiency;
		this.skillName = skillName;
	}

	public Skill(int skillID, String proficiency) {
		super();
		this.skillID = skillID;
		this.proficiency = proficiency;
	}

	public Skill() {
		super();
	}

	public int getSkillID() {
		return skillID;
	}

	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}