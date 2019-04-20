package com.erp.audit.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.audit.model.CentralizedLogs;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CentralizedLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Long addCentralizedLog(final CentralizedLogs centralizedLogs) {
		log.debug("Running query addCentralizedLog {}", centralizedLogs);
		return (Long) sessionFactory.getCurrentSession().save(centralizedLogs);
	}

}
