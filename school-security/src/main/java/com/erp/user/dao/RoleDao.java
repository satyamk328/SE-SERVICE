package com.erp.user.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository("roleDetailsDao")
@Slf4j
public class RoleDao {
	@Autowired
	private SessionFactory sessionFactory;

}