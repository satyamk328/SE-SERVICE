package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.RolePermissionDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RolePermissionService {

	@Autowired
	private RolePermissionDao permissionDao;
	
}
