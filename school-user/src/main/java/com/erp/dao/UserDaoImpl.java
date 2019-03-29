package com.erp.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	@Value("${update_user_master_lock}")
	private String updateUserLockQuery;
	@Value("${select_user_detail_by_phone}")
	private String selectUserDetailsByPhoneQuery;
	@Value("${select_user_detail_by_email}")
	private String selectUserDetailsByEmailQuery;
	@Value("${update_user_password}")
	private String updateUserPasswordQuery;
	@Value("${upadte_logintime}")
	private String updateLoginLogoutTimeQuery;
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
	public User addUser(User user) {
		return user;
	}

	public User getUser(Long id) {
		log.debug("Running insert query for getUserDetails {}", selectUserByIdQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", id);
		return jdbcTemplate.queryForObject(selectUserByIdQuery, new BeanPropertyRowMapper<User>(User.class),
				parameters);
	}

	public User updateUser(User user) {
		return null;
	}

	public void deleteUser(Long id) {

	}

	@Transactional
	public int resetPassword(String uid, String pass) throws UnsupportedEncodingException {
		log.debug("Running insert query for getUserDetails {}", updateUserPasswordQuery);
		return jdbcTemplate.update(updateUserPasswordQuery, CommonUtil.encrypt(pass.trim()), uid);
	}

	@Transactional(readOnly = true)
	public User loginauthentication(User user) throws UnsupportedEncodingException {
		try {
			final MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("password", CommonUtil.encrypt(user.getPassword()));
			if (DataUtils.validatePhoneNumber(user.getEmail())) {
				log.debug("Running insert query for authUser {}", selectUserDetailsByPhoneQuery);
				parameters.addValue("phone", user.getEmail());
				user = jdbcTemplateObject.queryForObject(selectUserDetailsByPhoneQuery, parameters,
						new BeanPropertyRowMapper<User>(User.class));
			} else {
				log.debug("Running insert query for authUser {}", selectUserDetailsByEmailQuery);
				parameters.addValue("email", user.getEmail());
				user = jdbcTemplateObject.queryForObject(selectUserDetailsByEmailQuery, parameters,
						new BeanPropertyRowMapper<User>(User.class));
			}
			return user;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public int logOut(String ip, String uid) {
		log.debug("Running insert query for addUser {}", updateLoginLogoutTimeQuery);
		return jdbcTemplate.update(updateLoginLogoutTimeQuery, new Object[] { ip, uid });
	}
}