package com.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.service.RolePrivilegeService;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;
import com.erp.user.model.RolePrivilege;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api/v0/rolePrivileges")
public class RolePrivilegeController {

	@Autowired
	private RolePrivilegeService rolePrivilegeService;

	@GetMapping("/{roleId}")
	public ResponseEntity<RestResponse<RolePrivilege>> getPrivilegeByRoleId(
			@PathVariable(name = "roleId", required = true) Long roleId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(),
				"Fetch privileges for a specific role Successfully");
		RolePrivilege rolePrivilege = rolePrivilegeService.getPrivilegeByRoleId(roleId);
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse<>(rolePrivilege, status), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<RestResponse<List<RolePrivilege>>> getAllPrivileges() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(),
				"Fetch privileges for a specific role Successfully");
		List<RolePrivilege> rolePrivileges = rolePrivilegeService.getAllPrivileges();
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse<>(rolePrivileges, status), HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<RestResponse<Object>> updateAllPrivileges(@RequestBody RolePrivilege rolePrivileges) {
		log.info("call updateAllPrivileges {}", rolePrivileges);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Role update Successfully");
		int updateState = rolePrivilegeService.updateAllPrivileges(rolePrivileges);
		return new ResponseEntity<>(new RestResponse<>(updateState, status), HttpStatus.OK);
	}
}
