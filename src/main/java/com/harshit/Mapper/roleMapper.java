package com.harshit.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class roleMapper implements RowMapper<String> {
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getString("role");
	}
}