package com.erp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springjwt")
public class ResourceController {

	/*
	 * @Autowired private GenericService userService;
	 */

	@RequestMapping(value = "/cities")
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	public List<String> getUser() {

		return Arrays.asList("Ajay", "Chauhan");
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<String> getUsers() {
		return Arrays.asList("Satyam", "Kumar");
	}
}
