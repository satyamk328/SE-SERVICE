package com.erp.user.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.erp.user.model.User;

import lombok.extern.slf4j.Slf4j;

@Repository("userDetailsDao")
@Slf4j
public class UserDao {

	public List<User> getAllUsers() {
		return null;
	}

	public Optional<User> getUserById(Long userId) {
		return null;
	}

	public Optional<User> findByUsername(String userName) {
		return null;
	}

	public boolean updateUserDetails(Long userId, User user) {
		return true;
	}
}