package com.erp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ApplicationRoleMenuVO {
	
    private Long id;	
    private Long roleMenuId;
    private String name;
    private String displayName;
    private Boolean isWriteAccess;
    private Boolean isReadAccess;
    private String parentName;
    private List<ApplicationRoleMenuVO> links;
    
    public ApplicationRoleMenuVO(RoleMenuMapping roleMenuMapping){
    	
		this.isReadAccess = roleMenuMapping.getIsReadAccess();
		this.isWriteAccess = roleMenuMapping.getIsWriteAccess();
		this.id = roleMenuMapping.getMenuId();
		this.roleMenuId  = roleMenuMapping.getRoleMenuId();
    }
}
