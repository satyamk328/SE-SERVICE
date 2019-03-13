package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.dao.UserRepository;
import com.erp.user.model.User;

@Component
public class UserServiceImp implements UserService {
	
	/*@Autowired
	p*///rivate UserRepository userRepository;

	public void createUser(User user) {
		//userRepository.save(user);
	}

	public List<User> getUser() {
		return null;//(List<User>) userRepository.findAll();
	}

	public User findById(long id) {
		return null;
	}

	public User update(User user, long l) {
		return null;//userRepository.save(user);
	}

	public void deleteUserById(long id) {
		//userRepository.deleteById(id);
	}

	public User updatePartially(User user, long id) {
		User usr = findById(id);
		usr.setCountry(user.getCountry());
		return null;//userRepository.save(usr);
	}

}
