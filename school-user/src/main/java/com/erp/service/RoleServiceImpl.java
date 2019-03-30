package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.RoleDaoImpl;
import com.erp.model.Role;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDaoImpl roleDao;

	@Override
	public Role getRole(Long id) {
		return roleDao.getRole(id);
	}

	@Override
	public long addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	public long updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	@Override
	public long deleteRole(Long id) {
		return roleDao.deleteRole(id);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public Role getRole(String roleName) {
		return roleDao.getRole(roleName);
	}

}