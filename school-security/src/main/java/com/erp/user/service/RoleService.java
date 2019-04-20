package com.erp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.user.dao.RoleDao;

@Service(value = "roleService")
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	
}