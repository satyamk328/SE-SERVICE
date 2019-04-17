package com.erp.menu.dao;

import java.util.List;

import com.erp.menu.model.MenuStructure;

public interface MenuStructureDao {
	
	public List<MenuStructure> fetchMenuStructureDetails();
}
