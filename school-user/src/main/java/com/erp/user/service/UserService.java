package com.erp.user.service;

import java.util.List;

import com.erp.user.model.Login;
import com.erp.user.model.User;

public interface UserService {

	public User loginAuthentication(String email);

	public User getUser(Long id);

	public long addUser(User user);

	public long updateUser(User user);

	public long deleteUser(Long id);

	public List<User> getAllUsers();

	public long unLockUser(Long userId);

	public long resetPassword(Long userId, String pass);

	public long addLoginDetail(Login login);

	public long updateLoginDetails(Long userId);

	public void prepareLogin(Login login, User user);

	public long lockUser(long userId, int isLock, int attempt);
}