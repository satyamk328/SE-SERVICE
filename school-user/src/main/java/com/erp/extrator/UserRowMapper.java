package com.erp.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.erp.model.User;

/**
 * @author Satyam Kumar
 *
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getLong("UserId"));
		user.setLoginId(rs.getString("LoginId"));
        user.setFirstName(rs.getString("FirstName"));
        user.setLastName(rs.getString("LastName"));
        user.setPassword(rs.getString("Password"));
        user.setAddress(rs.getString("Address"));
        user.setEmail(rs.getString("Email"));
        user.setPhoneNumber(rs.getLong("PhoneNumber"));
        user.setRoleId(rs.getLong("RoleId"));
        user.setCity(rs.getString("City"));
        user.setState(rs.getString("State"));
        user.setIsActive(rs.getBoolean("IsActive"));
        user.setIsLock(rs.getBoolean("IsLock"));
		return user;
	}

}
