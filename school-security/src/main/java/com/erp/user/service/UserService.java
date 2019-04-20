package com.erp.user.service;

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

	public Boolean existsByUsername(String userName) {
		return true;
	}

	public Boolean existsByEmail(String email) {
		return true;
	}

	public User getIdByName(String userName) {
		return  userDao.findByUsername(userName);
	}
}