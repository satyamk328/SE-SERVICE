package com.erp.service;

import java.util.List;
import java.util.stream.Collectors;

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
	public boolean checkRoleNameExists(final long roleId, final String roleName){
		List<Role> roleList = roleDao.getAllRoles();
		if(null != roleList && !roleList.isEmpty()) {
			return !roleList.stream().filter(userRole -> (userRole.getRoleName().equalsIgnoreCase(roleName)
					&& !userRole.getRoleId().equals(roleId))).collect(Collectors.toList()).isEmpty();
		}
		return false;
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