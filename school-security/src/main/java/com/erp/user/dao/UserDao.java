package com.erp.user.dao;

import java.util.List;

import org.hibernate.Session;
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

	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		log.info("Running query getAllUsers");
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

	@Transactional(readOnly = true)
	public User getUserById(Long userId) {
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userId", userId)).uniqueResult();
	}

	@Transactional(readOnly = true)
	public User findByUsername(String userName) {
		if (dataUtils.validatePhoneNumber(userName)) {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("phoneNumber", userName)).uniqueResult();
		} else {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("email", userName)).uniqueResult();
		}
	}

	public boolean updateUserDetails(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush();
		return true;
	}

	public Long save(User user) {
		return (Long) sessionFactory.getCurrentSession().save(user);
	}

	@Transactional
	public void deleteUser(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		session.delete(user);
		session.flush();
	}

	public int resetPassword(Long userId, String pass) {
		return sessionFactory.getCurrentSession().createQuery("Update User set password=:password where userId=:userId")
				.setParameter("password", pass).setParameter("userId", userId).executeUpdate();
	}

	public int unLockUser(Long userId) {
		return sessionFactory.getCurrentSession().createQuery("Update User set isLock = 1 where userId=:userId")
				.setParameter("userid", userId).executeUpdate();
	}
}