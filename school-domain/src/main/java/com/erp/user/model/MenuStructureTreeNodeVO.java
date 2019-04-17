package com.erp.user.model;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuStructureTreeNodeVO implements Comparable<MenuStructureTreeNodeVO> {
	private String id;
	private String name;
	private String displayName;
	private String parentName;
	private String dataParent;
	private String iconClassName;
	private String sectionClassName;
	private String path;
	@JsonIgnore
	private int sequence;
	private Set<MenuStructureLink> links;
	private Set<MenuStructureTreeNodeVO> children;
	

	public MenuStructureTreeNodeVO(final String name) {
		super();
		this.name = name;
	}
	
	public MenuStructureTreeNodeVO(final MenuStructure menuDao) {
		this.id = menuDao.getMenuId();
		this.name = menuDao.getItem();
		this.displayName = menuDao.getItemDisplayName();
		this.parentName = menuDao.getParentItem();
		this.dataParent = menuDao.getDataParentClass();
		this.iconClassName = menuDao.getIconClass();
		this.sectionClassName = menuDao.getSectionClass();
		this.path = menuDao.getRelativePath();
		this.sequence = menuDao.getDisplayPriority();
	}
	
	public Set<MenuStructureLink> getLinks() {
		if(links == null) {
			links = new TreeSet<>();
		}
		return links;
	}
	
	public Set<MenuStructureTreeNodeVO> getChildren() {
		if(children == null) {
			children = new TreeSet<>();
		}
		return children;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuStructureTreeNodeVO other = (MenuStructureTreeNodeVO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			{return false;}
		return true;
	}

	@Override
	public int compareTo(MenuStructureTreeNodeVO other) {
		return this.sequence-other.sequence;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\""+ id+"\",\"name\":\"" + name + "\", \"displayName\":\""+displayName+"\", \"parentName\":\"" + parentName + "\", \"dataParent\":\""+dataParent+"\", \"iconClassName\":\""+iconClassName+"\", \"sectionClassName\":\""+sectionClassName+"\", \"path\":\""+path+"\", \"links\":"+links+ ",\"children\":"+children+ "}";
	}

}
