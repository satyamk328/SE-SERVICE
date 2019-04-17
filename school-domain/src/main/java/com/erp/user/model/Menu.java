package com.erp.user.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	private Long menuId;
	private String item;
	private String itemDisplayName;
	private String description;
	private Long parentId;
	private int displayPriority;
	
	private Boolean isWriteAccess;
	private Boolean isReadAccess;
	
	@JsonIgnore
	private List<Menu> links;
}
