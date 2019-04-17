package com.erp.menu.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuStructure {
	private String menuId;
	private String item;
	private String itemDisplayName;
	private String parentItem;
	private String dataParentClass;
	private String iconClass;
	private String sectionClass;
	private String relativePath;
	private Boolean isLink;
	private int displayPriority;
	
	public Boolean isLink() {
		return isLink;
	}
	@Override
	public String toString() {
		return "MenuStructure [menuId="+ menuId +"item=" + item + ", itemDisplayName=" + itemDisplayName + ", parentItem=" + parentItem
				+ ", dataParentClass=" + dataParentClass + ", iconClass=" + iconClass + ", sectionClass=" + sectionClass
				+ ", relativePath=" + relativePath + ", isLink=" + isLink + ", displayPriority=" + displayPriority + "]";
	}
	
	
}