package com.erp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.model.Role;
import com.erp.service.RoleService;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/roles")
@Slf4j
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/")
	public ResponseEntity<RestResponse<List<Role>>> getAllRoles() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<Role> roles = roleService.getAllRoles();
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse<>(roles, status), HttpStatus.OK);
	}

	@GetMapping("/{roleId}")
	public ResponseEntity<RestResponse<Role>> getRole(@PathVariable(name = "roleId", required = true) Long roleId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch Records Successfully");
		Role role = roleService.getRole(roleId);
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse<>(role, status), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<RestResponse<Role>> addRole(@RequestBody(required = true) Role role, Principal principal) {
		log.info("call registration {}", role);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Role added Successfully");
		if (roleService.getRole(role.getRoleName()) != null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Role Name is already exist");
			return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Long roleId = roleService.addRole(role);
			role.setRoleId(roleId);
			return new ResponseEntity<>(new RestResponse<>(role, status), HttpStatus.OK);
		}
	}

	@PutMapping("/{roleId}")
	public ResponseEntity<RestResponse<Role>> updateRole(@RequestBody(required = true) Role role,
			@PathVariable("roleId") final Long roleId, Principal principal) {
		log.info("call registration {}", role);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Role update Successfully");
		if (roleService.checkRoleNameExists(roleId, role.getRoleName())) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Role Name is already exist");
			return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			long i = roleService.updateRole(role);
			role.setRoleId(roleId);
			return new ResponseEntity<>(new RestResponse(i, status), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{roleId}")
	public ResponseEntity<RestResponse<Role>> deleteRole(@PathVariable("roleId") final Long roleId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Record deleted Successfully");
		Long row = roleService.deleteRole(roleId);
		return new ResponseEntity<>(new RestResponse(row, status), HttpStatus.OK);
	}

}
