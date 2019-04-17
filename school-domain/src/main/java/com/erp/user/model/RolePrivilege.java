package com.erp.user.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePrivilege {
	private Long roleMenuId;
	private Long roleId;
	private Long roleName;
	private Long description;
	
	private List<Menu> menuData;
}
