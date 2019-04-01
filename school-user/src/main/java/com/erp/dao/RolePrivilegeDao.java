package com.erp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.model.RolePrivilege;

import lombok.extern.slf4j.Slf4j;

@Repository("rolePrivilegeDao")
@Slf4j
public class RolePrivilegeDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public RolePrivilege getPrivilegeByRoleId(Long roleId) {
		return null;
	}

	public List<RolePrivilege> getAllPrivileges() {
		return null;
	}

	public int updateAllPrivileges(RolePrivilege rolePrivileges) {
		return 0;
	}
}
