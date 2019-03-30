package com.erp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.extrator.RoleRowMapper;
import com.erp.model.Role;

import lombok.extern.slf4j.Slf4j;

@Repository("roleDetailsDao")
@Slf4j
public class RoleDaoImpl {

	@Value("${insert_role_detail}")
	private String insertRoleDetailQuery;

	@Value("${select_roleByid}")
	private String selectRoleByIdQuery;

	@Value("${update_role_details}")
	private String updateRoleDetails;

	@Value("${delete_role_query}")
	private String deleteRoleQuery;

	@Value("${select_role_history}")
	private String selectAllRoleQuery;

	@Value("${select_roleByName}")
	private String selectRoleByNameQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public Role getRole(Long id) {
		log.debug("Running insert query for getRole {}", selectRoleByIdQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("roleId", id);
		List<Role> roles = jdbcTemplateObject.query(selectRoleByIdQuery, parameters, new RoleRowMapper());
		return (roles != null && !roles.isEmpty()) ? roles.get(0) : null;
	}

	@Transactional
	public long addRole(Role role) {
		log.debug("Running insert query for addRole {}", insertRoleDetailQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(role);
		jdbcTemplateObject.update(insertRoleDetailQuery, parameters, holder);
		return (holder.getKey() == null) ? 0 : holder.getKey().intValue();
	}

	@Transactional
	public long updateRole(Role role) {
		log.debug("Running upadte query for updateRole {}", updateRoleDetails);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("roleId", role.getRoleId());
		parameters.addValue("roleName", role.getRoleName());
		parameters.addValue("descritpion", role.getDescription());

		return jdbcTemplateObject.update(updateRoleDetails, parameters);
	}

	@Transactional
	public long deleteRole(Long id) {
		log.debug("Running upadte query for deleteRole {}", deleteRoleQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("roleId", id);
		return jdbcTemplateObject.update(deleteRoleQuery, parameters);
	}

	@Transactional(readOnly = true)
	public List<Role> getAllRoles() {
		log.debug("Running insert query for getAllRoles: {}", selectAllRoleQuery);
		return jdbcTemplate.query(selectAllRoleQuery, new RoleRowMapper());
	}

	@Transactional(readOnly = true)
	public Role getRole(final String roleName) {
		log.debug("Running insert query for getRole {}", selectRoleByNameQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("roleName", roleName);
		List<Role> roles = jdbcTemplateObject.query(selectRoleByNameQuery, parameters, new RoleRowMapper());
		return (roles != null && !roles.isEmpty()) ? roles.get(0) : null;
	}
}