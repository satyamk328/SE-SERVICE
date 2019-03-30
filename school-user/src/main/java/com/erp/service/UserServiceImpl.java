package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.UserDaoImpl;
import com.erp.model.Login;
import com.erp.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDaoImpl userDao;

	public User loginauthentication(User user) {
		user = userDao.loginAuthentication(user);
		Login login = new Login();
		if (user != null) {
			prepareLogin(login, user);
			addLoginDetail(login);
		}
		return user;
	}

	private void prepareLogin(Login login, User user) {
		login.setAddress(user.getAddress());
		login.setCity(user.getCity());
		login.setState(user.getState());
		login.setUserName(user.getLoginId());
		login.setUserId(user.getUserId());
		login.setClientHost("localhost");
		login.setClientIp("127.0.0.1");
		login.setSessionId("");
	}

	@Override
	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	@Override
	public long addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public long updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public long deleteUser(Long id) {
		return userDao.deleteUser(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAllUser();
	}

	@Override
	public long unLockUser(Long userId) {
		return userDao.unLockUser(userId);
	}

	@Override
	public long resetPassword(Long userId, String pass) {
		return userDao.resetPassword(userId, pass);
	}

	@Override
	public long addLoginDetail(Login login) {
		return userDao.addLoginDetail(login);
	}

	@Override
	public long updateLoginDetails(Long userId) {
		return userDao.updateLoginDetails(userId);
	}

}