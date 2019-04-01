package com.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.model.ApplicationMenu;
import com.erp.model.Role;
import com.erp.service.MenuService;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/menu")
@Slf4j
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/")
	public ResponseEntity<RestResponse<List<ApplicationMenu>>> getAllMenus() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<ApplicationMenu> roles = menuService.getAllMenus();
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse<>(roles, status), HttpStatus.OK);
	}
	
}
