package com.erp.service;


import java.util.Set;

import com.erp.user.model.MenuStructureTreeNodeVO;


public interface MenuStructureService {
	
	Set<MenuStructureTreeNodeVO> getAllMenusAndLinks();
}
