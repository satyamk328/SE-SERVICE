package com.erp.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.erp.model.Role;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setRoleId(rs.getLong("RoleId"));
		role.setRoleName(rs.getString("RoleName"));
		role.setDescription(rs.getString("Description"));
		role.setIsActive(rs.getBoolean("IsActive"));
		return role;
	}

}
