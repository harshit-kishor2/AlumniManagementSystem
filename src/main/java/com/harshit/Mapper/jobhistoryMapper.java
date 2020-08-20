package com.harshit.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.harshit.model.JobHistory;

public class jobhistoryMapper implements RowMapper<JobHistory> {

	@Override
	public JobHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobHistory mappedHistory = new JobHistory();
		mappedHistory.setRid(rs.getString("rid"));
		mappedHistory.setPosition(rs.getString("position"));
		mappedHistory.setCompanyName(rs.getString("companyname"));
		mappedHistory.setAddress(rs.getString("address"));
		mappedHistory.setStartDate(rs.getString("startdate"));
		mappedHistory.setEndDate(rs.getString("enddate"));
		mappedHistory.setCurrentlyEmployed(rs.getBoolean("currentlyemplyed"));
		return mappedHistory;
	}
}