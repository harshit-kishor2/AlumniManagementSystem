package com.harshit.Dao;

/*
 * This class is intended to directly map results from the
 * userskills table in the database.
 */
public class UserSkill {

	private String rid;
	private String skillName;
	private String proficiency;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}
}