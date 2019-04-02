package com.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuStructureLink implements Comparable<MenuStructureLink>{
	
	private String path;
	private String name;
	private String displayName;
	private String parentName;
	@JsonIgnore
	private int sequence;
	
	public MenuStructureLink(final MenuStructureTreeNodeVO menuVO) {
		this.path = menuVO.getPath();
		this.name = menuVO.getName();
		this.displayName = menuVO.getDisplayName();
		this.parentName = menuVO.getParentName();
		this.sequence = menuVO.getSequence();
	}
	
	@Override
	public int compareTo(MenuStructureLink other) {
		return this.sequence-other.sequence;
	}
	
	
	
	@Override
	public String toString() {
		return "{\"parentName\"" +parentName+"\",\"path\":\"" + path + "\", \"name\":\"" + name + "\",\"displayName\":\""+displayName+"\"}";
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
		MenuStructureLink other = (MenuStructureLink) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			{return false;}
		return true;
	}
	
	
}
