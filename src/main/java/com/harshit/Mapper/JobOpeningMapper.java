package com.harshit.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.harshit.model.Company;
import com.harshit.model.InternshipJobOpening;

public class JobOpeningMapper implements RowMapper<InternshipJobOpening> {

	@Override
	public InternshipJobOpening mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		InternshipJobOpening jobOpening = new InternshipJobOpening();
		jobOpening.setJobID(rs.getInt("jobid"));
		jobOpening.setPosition(rs.getString("position"));
		jobOpening.setLocation(rs.getString("location"));
		jobOpening.setProficiancy(rs.getString("proficiancy"));
		jobOpening.setStartDate(rs.getDate("startdate"));
		jobOpening.setPayrate(rs.getString("payrate"));
		jobOpening.setJobDescription(rs.getString("jobdescription"));
		jobOpening.setHoursPerWeek(rs.getString("hoursperweek"));
		jobOpening.setEndDate(rs.getDate("enddate"));
		Company company = new Company();
		company.setCompanyName(rs.getString("companyname"));
		company.setPhoneNumber(rs.getString("phone_number"));
		jobOpening.setCompanyName(company);
		return jobOpening;
	}
}