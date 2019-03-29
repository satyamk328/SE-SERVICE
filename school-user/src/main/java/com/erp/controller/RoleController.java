package com.erp.controller;

import java.security.Principal;
import java.util.List;

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
import com.erp.spring.model.RestResponse;

@RestController
@RequestMapping(value = "/api/v0/roles")
public class RoleController {

	@GetMapping("/")
	public ResponseEntity<RestResponse<List<Role>>> getAllRoles() {
		HttpStatus status = HttpStatus.OK;

		return null;

	}

	@PostMapping("/")
	public ResponseEntity<RestResponse<Role>> addUserRole(@RequestBody(required = true) Role role,
			Principal principal) {
		HttpStatus status = HttpStatus.PRECONDITION_FAILED;
		String messageCode = null;
		return null;
	}

	@DeleteMapping("/{roleId}")
	public ResponseEntity<RestResponse<Role>> deleteUserRole(@PathVariable("roleId") final Long roleId) {
		HttpStatus status = HttpStatus.PRECONDITION_FAILED;
		String messageCode = null;
		return null;
	}

	@PutMapping("/{roleId}")
	public ResponseEntity<RestResponse<Role>> updateUserRole(@RequestBody(required = true) Role role,
			@PathVariable("roleId") final Long roleId, Principal principal) {
		HttpStatus status = HttpStatus.PRECONDITION_FAILED;
		String messageCode = null;
		return null;
	}

}
