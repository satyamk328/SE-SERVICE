package com.erp.service;

import java.util.List;

import com.erp.user.model.Role;

public interface RoleService {

	public Role getRole(Long id);

	public long addRole(Role role);

	public long updateRole(Role role);

	public long deleteRole(Long id);

	public List<Role> getAllRoles();

	public Role getRole(final String roleName);
	
	public boolean checkRoleNameExists(final long roleId, final String roleName);
}