package com.erp.audit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.user.model.JwtModel;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserJwtTokenDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Long insertJwt(final JwtModel jwt) {
		return (Long) sessionFactory.getCurrentSession().save(jwt);
	}

	@Transactional
	public int removeJwt(final JwtModel jwt) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("Delete from JwtModel where token=:Token").setParameter("Token", jwt.getToken())
				.executeUpdate();
	}

	public boolean checkJwt(final String jwt) {
		List<Integer> isValid = (List<Integer>) sessionFactory.getCurrentSession().createCriteria(JwtModel.class)
				.add(Restrictions.eq("token", jwt)).add(Restrictions.gt("expirationTime", "now()"))
				.add(Restrictions.gt("valid", "1")).setProjection(Projections.property("valid"));
		log.info("Query run for checkJwt ");
		if (isValid.size() == 0) {
			return false;
		}
		return isValid.get(0) == 1 ? true : false;
	}

	public int updateJwtIsValid(final JwtModel jwt) {
		return 0;
	}
}
