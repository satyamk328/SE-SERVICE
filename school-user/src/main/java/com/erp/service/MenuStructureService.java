package com.erp.service;


import java.util.Set;

import com.erp.model.MenuStructureTreeNodeVO;


public interface MenuStructureService {
	
	Set<MenuStructureTreeNodeVO> getAllMenusAndLinks();
}
