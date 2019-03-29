package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.UserDaoImpl;
import com.erp.model.User;

import lombok.Getter;
import lombok.Setter;

@Service(value = "userService")
@Setter
@Getter
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDaoImpl userDao;

	@Override
	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	@Override
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAllUser();
	}

}