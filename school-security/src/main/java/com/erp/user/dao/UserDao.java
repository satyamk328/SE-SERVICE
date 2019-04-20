package com.erp.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.user.model.User;
import com.erp.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

@Repository("userDetailsDao")
@Slf4j
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataUtils dataUtils;

	@Transactional(readOnly=true)
	public List<User> getAllUsers() {
		log.info("Running query getAllUsers");
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

	@Transactional(readOnly=true)
	public User getUserById(Long userId) {
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userId", userId)).uniqueResult();
	}

	@Transactional(readOnly=true)
	public User findByUsername(String userName) {
		if (dataUtils.validatePhoneNumber(userName)) {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("phoneNumber", userName)).uniqueResult();
		} else {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("email", userName)).uniqueResult();
		}
	}

	public boolean updateUserDetails(Long userId, User user) {
		return true;
	}
}