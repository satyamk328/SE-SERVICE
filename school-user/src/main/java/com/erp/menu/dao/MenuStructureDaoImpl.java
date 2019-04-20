package com.erp.menu.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.erp.menu.model.MenuStructure;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MenuStructureDaoImpl implements MenuStructureDao {
	

	@Override
	public List<MenuStructure> fetchMenuStructureDetails() {
		return null;
	}
	
}
