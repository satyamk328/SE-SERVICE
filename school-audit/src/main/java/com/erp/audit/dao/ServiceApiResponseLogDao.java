package com.erp.audit.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.audit.model.ServiceApiAuditLog;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ServiceApiResponseLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Long addLog(ServiceApiAuditLog apiAuditLog) {
		log.debug("Running query addLog {}", apiAuditLog);
		return (Long) sessionFactory.getCurrentSession().save(apiAuditLog);
	}
}
