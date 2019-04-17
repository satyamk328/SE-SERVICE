package com.erp.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	private Long roleId;
	private String roleName;
	private String description;
	private Boolean isActive;

	public Role(String roleName) {
		this.roleName = roleName;
	}

}