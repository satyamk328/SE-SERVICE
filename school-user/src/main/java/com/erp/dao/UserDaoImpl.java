package com.erp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.extrator.UserRowMapper;
import com.erp.model.Login;
import com.erp.model.User;
import com.erp.utils.CommonUtil;
import com.erp.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

@Repository("userDetailsDao")
@Slf4j
public class UserDaoImpl {

	@Value("${select_user_history}")
	private String selectAllUserQuery;
	
	@Value("${insert_user_detail}")
	private String insertUserDetailQuery;
	
	@Value("${update_user_details}")
	private String updateUserDetails;
	
	@Value("${delete_user_query}")
	private String deleteUserQuery;
	
	@Value("${update_user_master_lock}")
	private String updateUserLockQuery;
	
	@Value("${update_user_attempt}")
	private String updateUserAttemptQuery;
	
	@Value("${select_user_detail_by_phone}")
	private String selectUserDetailsByPhoneQuery;
	
	@Value("${select_user_detail_by_email}")
	private String selectUserDetailsByEmailQuery;
	
	@Value("${reset_user_password}")
	private String resetUserPasswordQuery;
	
	@Value("${insert_login_details}")
	private String insertLoginDetailsQuery;
	
	@Value("${update_login_details}")
	private String updateLoginDetails;
	
	@Value("${select_userbyid}")
	private String selectUserByIdQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<User> findAllUser() {
		log.debug("Running insert query for addUser: {}", selectAllUserQuery);
		return jdbcTemplate.query(selectAllUserQuery, new BeanPropertyRowMapper<User>(User.class));
	}

	@Transactional
	public long addUser(User user) {
		log.debug("Running insert query for addUser {}", insertUserDetailQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
		jdbcTemplateObject.update(insertLoginDetailsQuery, parameters, holder);
		return (holder.getKey() == null) ? 0 : holder.getKey().intValue();
	}

	public User getUser(Long id) {
		log.debug("Running insert query for getUserDetails {}", selectUserByIdQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", id);
		List<User> users = jdbcTemplateObject.query(selectUserByIdQuery, parameters, new UserRowMapper());
		return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}

	@Transactional
	public long updateUser(User user) {
		log.debug("Running upadte query for updateUser {}", updateUserDetails);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("loginId", user.getLoginId());
		parameters.addValue("firstName", user.getFirstName());
		parameters.addValue("lastName", user.getLastName());
		parameters.addValue("password", CommonUtil.decrypt(user.getPassword()));
		parameters.addValue("email", user.getEmail());
		parameters.addValue("isactive", user.getIsActive());
		parameters.addValue("isLock", user.getIsLock());
		parameters.addValue("address", user.getAddress());
		parameters.addValue("city", user.getCity());
		parameters.addValue("state", user.getState());
		parameters.addValue("phoneNumber", user.getPhoneNumber());
		parameters.addValue("roleId", user.getRoleId());
		parameters.addValue("userId", user.getUserId());
		return jdbcTemplateObject.update(updateUserDetails, parameters);
	}

	@Transactional
	public long unLockUser(Long userId) {
		log.debug("Running upadte query for updateUser {}", updateUserLockQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		return jdbcTemplateObject.update(updateUserLockQuery, parameters);
	}

	@Transactional
	public long deleteUser(Long id) {
		log.debug("Running upadte query for updateUser {}", deleteUserQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", id);
		return jdbcTemplateObject.update(deleteUserQuery, parameters);
	}

	@Transactional
	public long resetPassword(Long userId, String pass) {
		log.debug("Running reset query for resetPassword {}", resetUserPasswordQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("password", CommonUtil.decrypt(pass));
		parameters.addValue("userId", userId);
		return jdbcTemplate.update(resetUserPasswordQuery, parameters);
	}

	@Transactional(readOnly = true)
	public User loginAuthentication(String email) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		List<User> users = new ArrayList<>();
		if (DataUtils.validatePhoneNumber(email)) {
			log.debug("Running insert query for authUser {}", selectUserDetailsByPhoneQuery);
			parameters.addValue("phone", email);
			users = jdbcTemplateObject.query(selectUserDetailsByPhoneQuery, parameters, new UserRowMapper());
		} else {
			log.debug("Running insert query for authUser {}", selectUserDetailsByEmailQuery);
			parameters.addValue("email", email);
			users = jdbcTemplateObject.query(selectUserDetailsByEmailQuery, parameters, new UserRowMapper());
		}
		return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}

	@Transactional
	public long addLoginDetail(Login login) {
		log.debug("Running insert query for insertLoginDetailsQuery {}", insertLoginDetailsQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(login);
		jdbcTemplateObject.update(insertLoginDetailsQuery, parameters, holder);
		return (holder.getKey() == null) ? 0 : holder.getKey().intValue();
	}

	@Transactional
	public long updateLoginDetails(Long userId) {
		log.debug("Running upadte query for updateLoginDetails {}", updateLoginDetails);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		return jdbcTemplateObject.update(updateLoginDetails, parameters);
	}
	
	public long lockUser(long userId, int isLock, int attempt) {
		log.debug("Running upadte query for lockUser {}", updateUserAttemptQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		parameters.addValue("isLock", isLock);
		parameters.addValue("attempt", attempt);
		return jdbcTemplateObject.update(updateUserAttemptQuery, parameters);
	}
}