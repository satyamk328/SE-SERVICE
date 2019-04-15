package com.erp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleMenuMapping {
	private Boolean isReadAccess;
	private Boolean isWriteAccess;
	private Long menuId;
	private Long roleMenuId;

}
