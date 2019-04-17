package com.erp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.user.dao.RolePrivilegeDao;
import com.erp.user.model.RolePrivilege;

@Service
public class RolePrivilegeService {

	@Autowired
	private RolePrivilegeDao rolePrivilegeDao;

	public RolePrivilege getPrivilegeByRoleId(Long roleId) {
		return rolePrivilegeDao.getPrivilegeByRoleId(roleId);
	}

	public List<RolePrivilege> getAllPrivileges() {
		return rolePrivilegeDao.getAllPrivileges();
	}

	public int updateAllPrivileges(RolePrivilege rolePrivileges) {
		return rolePrivilegeDao.updateAllPrivileges(rolePrivileges);
	}
}
