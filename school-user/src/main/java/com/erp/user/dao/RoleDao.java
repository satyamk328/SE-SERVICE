package com.erp.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.user.model.Role;
import lombok.extern.slf4j.Slf4j;

@Repository("roleDetailsDao")
@Slf4j
public class RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public Role getRole(Long roleId) {
		log.debug("Running query getRole");
		return sessionFactory.getCurrentSession().get(Role.class, roleId);
	}

	@Transactional
	public Long addRole(Role role) {
		return (Long) sessionFactory.getCurrentSession().save(role);
	}

	@Transactional
	public void updateRole(Role role) {
		sessionFactory.getCurrentSession().saveOrUpdate(role);
	}

	@Transactional
	public void deleteRole(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		Role role = sessionFactory.getCurrentSession().get(Role.class, roleId);
		session.delete(role);
	}

	@Transactional(readOnly = true)
	public List<Role> getAllRoles() {
		return sessionFactory.getCurrentSession().createCriteria(Role.class).list();
	}

	@Transactional(readOnly = true)
	public Role getRole(final String roleName) {
		return (Role) sessionFactory.getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("roleName", roleName)).uniqueResult();
	}
}