package com.erp.service;

import java.util.List;

import com.erp.model.Login;
import com.erp.model.User;

public interface UserService {

	public User loginauthentication(User user);

	public User getUser(Long id);

	public long addUser(User user);

	public long updateUser(User user);

	public long deleteUser(Long id);

	public List<User> getAllUsers();

	public long unLockUser(Long userId);

	public long resetPassword(Long userId, String pass);

	public long addLoginDetail(Login login);

	public long updateLoginDetails(Long userId);
}