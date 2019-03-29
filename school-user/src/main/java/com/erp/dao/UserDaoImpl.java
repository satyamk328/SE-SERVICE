package com.erp.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.extrator.UserRowMapper;
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

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<User> findAllUser() {
		log.debug("Running insert query for addUser: {}", selectAllUserQuery);
		return jdbcTemplate.queryForList(selectAllUserQuery, User.class);
	}

	@Transactional
	public User addUser(User user) {
		return user;
	}

	public User getUser(Long id) {
		return null;
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

	@Transactional
	public User loginauthentication(User user) throws UnsupportedEncodingException {
		try {
			if (DataUtils.validatePhoneNumber(user.getEmail())) {
				log.debug("Running insert query for authUser {}", selectUserDetailsByPhoneQuery);
				user = jdbcTemplate.queryForObject(selectUserDetailsByPhoneQuery,
						new Object[] { user.getEmail(), CommonUtil.encrypt(user.getPassword()) }, new UserRowMapper());
			} else {
				log.debug("Running insert query for authUser {}", selectUserDetailsByEmailQuery);
				user = jdbcTemplate.queryForObject(selectUserDetailsByEmailQuery,
						new Object[] { user.getEmail(), CommonUtil.encrypt(user.getPassword()) }, new UserRowMapper());
			}
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Transactional
	public int logOut(String ip, String uid) {
		log.debug("Running insert query for addUser {}", updateLoginLogoutTimeQuery);
		return jdbcTemplate.update(updateLoginLogoutTimeQuery, new Object[] { ip, uid });
	}
}