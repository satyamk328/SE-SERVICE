package com.erp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository("rolePermissionDao")
@Slf4j
public class RolePermissionDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;
	
}
