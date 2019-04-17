package com.erp.menu.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.menu.model.MenuStructure;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MenuStructureDaoImpl implements MenuStructureDao {
	
	@Value("${menu_structure.fetch.all}")
	private String fetchAllMenuStructureDetails;
	
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Override
	public List<MenuStructure> fetchMenuStructureDetails() {
		return jdbcTemplateObject.query(fetchAllMenuStructureDetails,
				new BeanPropertyRowMapper<>(MenuStructure.class));
	}
	
}
