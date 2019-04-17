package com.erp.menu.service;


import java.util.Set;

import com.erp.menu.model.MenuStructureTreeNodeVO;


public abstract class MenuStructureProcessTreeNodesTemplate {
	protected final MenuStructureTreeNodeVO root = new MenuStructureTreeNodeVO("root");
	
	public abstract void formTreeForAllMenus(final Set<MenuStructureTreeNodeVO> menus);
	public abstract void attachLinksToMenus(final Set<MenuStructureTreeNodeVO> links);
	
	public MenuStructureTreeNodeVO formTreeAttachLinksAndReturnRoot(final Set<MenuStructureTreeNodeVO> menus,final Set<MenuStructureTreeNodeVO> links) {
		formTreeForAllMenus(menus);
		attachLinksToMenus(links);
		return root;
	}}
