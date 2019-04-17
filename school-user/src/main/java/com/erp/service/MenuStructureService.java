package com.erp.service;


import java.util.Set;

import com.erp.menu.model.MenuStructureTreeNodeVO;


public interface MenuStructureService {
	
	Set<MenuStructureTreeNodeVO> getAllMenusAndLinks();
}
