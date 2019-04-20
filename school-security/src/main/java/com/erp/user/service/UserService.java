package com.erp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.auth.model.UserPrincipal;
import com.erp.user.dao.UserDao;
import com.erp.user.model.User;

@Service(value = "userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder encode;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null)
			new UsernameNotFoundException("User Not Found with -> username or email : " + username);
		System.out.println(encode.encode("smriti@123"));
		return UserPrincipal.build(user);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public Boolean existsByPhone(String userName) {
		if (userDao.findByUsername(userName) != null)
			return true;
		else
			return false;
	}

	public Boolean existsByEmail(String email) {
		if (userDao.findByUsername(email) != null)
			return true;
		else
			return false;
	}

	public Long saveUser(User user) {
		user.setPassword(encode.encode(user.getPassword()));
		return userDao.save(user);
	}

	public User getIdByName(String userName) {
		return userDao.findByUsername(userName);
	}

	public User getUserDetailsById(Long userId) {
		return userDao.getUserById(userId);
	}

	public User updateUserDetails(Long userId, User user) {
		if (userDao.getUserById(userId) != null) {
			user.setPassword(encode.encode(user.getPassword()));
			userDao.updateUserDetails(user);
			return user;
		} else {
			return null;
		}
	}

	public boolean deleteUser(Long userId) {
		userDao.deleteUser(userId);
		return true;
	}

	public int resetPassword(Long userId,String pass) {
		return userDao.resetPassword(userId, pass);
	}
	public int unLockUser(Long userId) {
		return userDao.unLockUser(userId);
	}
}