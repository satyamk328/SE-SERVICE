package com.erp.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.user.dao.RoleDao;
import com.erp.user.model.Role;

@Service(value = "roleService")
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public Role getRole(Long id) {
		return roleDao.getRole(id);
	}

	public Long addRole(Role role) {
		return roleDao.addRole(role);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public void deleteRole(Long id) {
		roleDao.deleteRole(id);
	}

	public boolean checkRoleNameExists(final long roleId, final String roleName) {
		List<Role> roleList = roleDao.getAllRoles();
		if (null != roleList && !roleList.isEmpty()) {
			return !roleList.stream().filter(userRole -> (userRole.getRoleName().equalsIgnoreCase(roleName)
					&& !userRole.getRoleId().equals(roleId))).collect(Collectors.toList()).isEmpty();
		}
		return false;
	}

	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	public Role getRole(String roleName) {
		return roleDao.getRole(roleName);
	}

}