package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.MenuDao;
import com.erp.model.ApplicationMenu;

@Service(value = "menuService")
public class MenuService {

	@Autowired
	private MenuDao menuDao;

	public List<ApplicationMenu> getAllMenus() {
		return menuDao.getAllMenus();
	}
}
