package com.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.erp.service.UserServiceImp;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;
import com.erp.user.model.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/api/v0/user" })
@Slf4j
public class UserController {

	@Autowired(required=true)
	@Qualifier("userService")
	private UserServiceImp userService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse<User>> getUserById(@PathVariable("id") long id) {
		log.info("Fetching User with id {}", id);
		RestStatus status = new RestStatus<>(HttpStatus.OK.toString(), "User Record successfully");
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<RestResponse<User>> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		log.info("Creating User {}", user.getUserName());
		RestStatus<?> status = new RestStatus<>(HttpStatus.OK.toString(), "User created successfully");
		userService.createUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<RestResponse<User>>(new RestResponse(user, status), headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get")
	public ResponseEntity<RestResponse<List<User>>> getAllUser() {
		RestStatus<?> status = new RestStatus<>(HttpStatus.OK.toString(), "Records fetch successfully");
		List<User> tasks = userService.getUser();
		return  new ResponseEntity<RestResponse<List<User>>>(new RestResponse(tasks, status), HttpStatus.OK);

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<RestResponse<User>> updateUser(@RequestBody User currentUser) {
		RestStatus<?> status = new RestStatus<>(HttpStatus.OK.toString(), "Records fetch successfully");
		User user = userService.findById(currentUser.getUserId());
		if (user == null) {
			new ResponseEntity<RestResponse<User>>(new RestResponse(user, status), HttpStatus.NOT_FOUND);
		}
		userService.update(currentUser, currentUser.getUserId());
		return new ResponseEntity<RestResponse<User>>(new RestResponse(user, status), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<RestResponse<User>> deleteUser(@PathVariable("id") long id) {
		RestStatus<?> status = new RestStatus<>(HttpStatus.OK.toString(), "Records delete successfully");
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<RestResponse<User>>(new RestResponse(user, status), HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<RestResponse<User>>(new RestResponse(user, status), HttpStatus.NO_CONTENT);
	}

	@PatchMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<RestResponse<User>> updateUserPartially(@PathVariable("id") long id, 
			@RequestBody User currentUser) {
		RestStatus<?> status = new RestStatus<>(HttpStatus.OK.toString(), "Records update successfully");
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<RestResponse<User>>(new RestResponse(user, status), HttpStatus.NOT_FOUND);
		}
		User usr = userService.updatePartially(currentUser, id);
		return new ResponseEntity<RestResponse<User>>(new RestResponse(user, status), HttpStatus.NOT_FOUND);
	}
}
