package com.erp.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.erp.user.model.RolePrivilege;

import lombok.extern.slf4j.Slf4j;

@Repository("rolePrivilegeDao")
@Slf4j
public class RolePrivilegeDao {


	public RolePrivilege getPrivilegeByRoleId(Long roleId) {
		return null;
	}

	public List<RolePrivilege> getAllPrivileges() {
		return null;
	}

	public int updateAllPrivileges(RolePrivilege rolePrivileges) {
		return 0;
	}
}
