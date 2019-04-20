package com.erp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.user.dao.UserDaoImpl;
import com.erp.user.model.Login;
import com.erp.user.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDaoImpl userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.loginAuthentication(username);
		if (user == null) {
			new UsernameNotFoundException("User not found with username or email : " + username);
		}
		// user.setRole(Arrays.asList(new Role(Roles.USER.toString())));
		return null;
	}

	@Override
	public User loginAuthentication(String email) {
		return userDao.loginAuthentication(email);
	}

	@Override
	public void prepareLogin(Login login, User user) {
		login.setAddress(user.getAddress());
		login.setCity(user.getCity());
		login.setState(user.getState());
		//login.setUserName(user.getLoginId());
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

	@Override
	public long lockUser(long userId, int isLock, int attempt) {
		return userDao.lockUser(userId, isLock, attempt);
	}

}