package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.erp.service.RolePermissionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RolePermissionController {

	@Autowired
	private RolePermissionService permissionService;
	
}
