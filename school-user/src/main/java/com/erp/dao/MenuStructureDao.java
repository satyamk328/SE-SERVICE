package com.erp.dao;

import java.util.List;

import com.erp.user.model.MenuStructure;

public interface MenuStructureDao {
	
	public List<MenuStructure> fetchMenuStructureDetails();
}
