package com.erp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;
import com.erp.user.model.User;
import com.erp.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/auth")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<RestResponse<List<User>>> getAllUsers() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<User> users = userService.getAllUsers();
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse<>(users, status), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<RestResponse<User>> getUser(@PathVariable(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch Records Successfully");
		User users = userService.getUserDetailsById(userId);
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse<>(users, status), HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<RestResponse<Object>> registration(@RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Registered Successfully");
		if (userService.existsByEmail(user.getEmail()) != null || userService.existsByPhone(user.getPhoneNumber())) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Please enter valid Email/Phone");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Long userId = userService.saveUser(user);
			user.setUserId(userId);
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<Object>> updateUserDetails(
			@PathVariable(name = "userId", required = true) Long userId, @RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User update Successfully");
		user = userService.updateUserDetails(userId, user);
		user.setUserId(userId);
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PutMapping(value = "/resetpassword/{userId}")
	public ResponseEntity<RestResponse<Object>> resetPassword(
			@PathVariable(name = "userId", required = true) Long userId,
			@RequestParam(name = "password", required = true) String pass) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Password change Successfully");
		int row = userService.resetPassword(userId, pass);
		return new ResponseEntity<>(new RestResponse<>(row, status), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<Object>> deleteUser(
			@PathVariable(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Record deleted Successfully");
		boolean flag = userService.deleteUser(userId);
		return new ResponseEntity<>(new RestResponse<>(flag, status), HttpStatus.OK);
	}

	@PutMapping(value = "/unLock/{userId}")
	public ResponseEntity<RestResponse<Object>> unLockUser(
			@PathVariable(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User unLock Successfully");
		int row = userService.unLockUser(userId);
		return new ResponseEntity<>(new RestResponse<>(row, status), HttpStatus.OK);
	}

}
