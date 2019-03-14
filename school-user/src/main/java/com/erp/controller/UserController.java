package com.erp.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/api/v0/user" })
@Slf4j
public class UserController {

	/*@Autowired
	private UserService userService;
*/
	@PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(method = RequestMethod.GET, value = "/users/extra")
	@ResponseBody
	public Map<String, Object> getExtraInfo(Authentication auth) {
		OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
		Map<String, Object> details = (Map<String, Object>) oauthDetails.getDecodedDetails();
		System.out.println("User organization is " + details.get("organization"));
		return details;
	}
/*
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
		return new ResponseEntity<RestResponse<List<User>>>(new RestResponse(tasks, status), HttpStatus.OK);

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
	}*/
}
