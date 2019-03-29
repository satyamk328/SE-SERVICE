package com.erp.service;

import java.util.List;

import com.erp.model.User;

public interface UserService {

	public User getUser(Long id);

	public User addUser(User user);

	public User updateUser(User user);

	public void deleteUser(Long id);

	public List<User> getAllUsers();
}