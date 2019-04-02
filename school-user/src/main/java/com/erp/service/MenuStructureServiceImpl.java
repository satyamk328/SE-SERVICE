package com.erp.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.MenuStructureDao;
import com.erp.model.MenuStructure;
import com.erp.model.MenuStructureTreeNodeVO;

@Service
public class MenuStructureServiceImpl implements MenuStructureService {
	
	@Autowired
	private MenuStructureDao menuStructureDao;
	
	@Autowired
	private MenuStructureProcessTreeNodesTemplate menuStructureProcess;
	
	@Override
	public Set<MenuStructureTreeNodeVO> getAllMenusAndLinks() {
		Map<String, Set<MenuStructureTreeNodeVO>> allMenusAndLinks = segregateAndPopulateMenusAndLinks(menuStructureDao.fetchMenuStructureDetails());
		return menuStructureProcess.formTreeAttachLinksAndReturnRoot(allMenusAndLinks.get("menus"),allMenusAndLinks.get("links")).getChildren();
	}
	
	public Map<String, Set<MenuStructureTreeNodeVO>> segregateAndPopulateMenusAndLinks(final List<MenuStructure> menus){
		Map<String, Set<MenuStructureTreeNodeVO>> allMenusAndLinks = null;
		
		if(null != menus && !menus.isEmpty()) {
			allMenusAndLinks = new HashMap<>();
			allMenusAndLinks.put("menus", menus.stream().filter(menu -> null != menu && !menu.isLink()).map(MenuStructureTreeNodeVO::new).collect(Collectors.toSet()));
			allMenusAndLinks.put("links", menus.stream().filter(menu -> null != menu && menu.isLink()).map(MenuStructureTreeNodeVO::new).collect(Collectors.toSet()));
		}
		
		return allMenusAndLinks;
	}
}