package com.erp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.auth.model.UserPrincipal;
import com.erp.user.dao.UserDao;
import com.erp.user.model.User;

@Service(value = "userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		return UserPrincipal.build(user);
	}

	public Boolean existsByUsername(String userName) {
		return true;
	}

	public Boolean existsByEmail(String email) {
		return true;
	}

	public User getIdByName(String userName) {
		return null;
	}
}