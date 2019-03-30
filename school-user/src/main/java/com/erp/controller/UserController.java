package com.erp.controller;

import java.io.UnsupportedEncodingException;
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

import com.erp.model.Login;
import com.erp.model.User;
import com.erp.service.UserService;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;
import com.erp.utils.CommonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@RestController
@RequestMapping(value = "/api/v0/auth")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	private static int attempt;

	@GetMapping("/users")
	public ResponseEntity<RestResponse<List<User>>> getAllUsers() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<User> users = userService.getAllUsers();
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse(users, status), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<RestResponse<User>> getUser(@PathVariable(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Get Records Successfully");
		User users = userService.getUser(userId);
		log.debug("Fetched record successfully");
		return new ResponseEntity<>(new RestResponse(users, status), HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<RestResponse<Object>> loginAuthentication(
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String pass) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Login Successfully");
		attempt++;
		User user = userService.loginAuthentication(email);
		if (user == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Invalid username or password!.");
			return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		userService.lockUser(user.getUserId(), 0, attempt);
		if (!user.getPassword().equals(CommonUtil.encrypt(pass))) {
			if (attempt != 0 && attempt < 3) {
				status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						"Invalid username and password. You have made count unsuccessful attempt(s). The maximum retry attempts allowed for login are 3. Password is case-sensitive."
								.replaceAll("count", String.valueOf(attempt)));
				return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
			} else if (attempt >= 3) {
				userService.lockUser(user.getUserId(), 1, attempt);
				status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						"You have been locked out for the day because of three invalid attempts during the day. You try to max number of attempt. You may unlock your username by contact system administrator!");
				return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		userService.lockUser(user.getUserId(), 0, 0);
		Login login = new Login();
		userService.prepareLogin(login, user);
		userService.addLoginDetail(login);
		return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
	}

	@PostMapping(value = "/singup")
	public ResponseEntity<RestResponse<Object>> registration(@RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Registered Successfully");
		if (userService.loginAuthentication(user.getEmail()) != null
				|| userService.loginAuthentication(String.valueOf(user.getPhoneNumber())) != null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Please enter valid Email/Phone");
			return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Long userId = userService.addUser(user);
			user.setUserId(userId);
			return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<Object>> updateUserDetails(
			@PathVariable(name = "userId", required = true) Long userId, @RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User update Successfully");
		long i = userService.updateUser(user);
		user.setUserId(userId);
		return new ResponseEntity<>(new RestResponse(i, status), HttpStatus.OK);
	}

	@PutMapping(value = "/resetpassword/{userId}")
	public ResponseEntity<RestResponse<Object>> resetPassword(
			@PathVariable(name = "userId", required = true) Long userId,
			@RequestParam(name = "password", required = true) String pass) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Password change Successfully");
		Long row = userService.resetPassword(userId, pass);
		return new ResponseEntity<>(new RestResponse(row, status), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<Object>> deleteUser(
			@PathVariable(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Record deleted Successfully");
		Long row = userService.deleteUser(userId);
		return new ResponseEntity<>(new RestResponse(row, status), HttpStatus.OK);
	}

	@PutMapping(value = "/unLock/{userId}")
	public ResponseEntity<RestResponse<Object>> unLockUser(
			@PathVariable(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User unLock Successfully");
		Long row = userService.unLockUser(userId);
		return new ResponseEntity<>(new RestResponse(row, status), HttpStatus.OK);
	}

	@PutMapping(value = "/logout/{userId}")
	public ResponseEntity<RestResponse<Object>> logOut(@PathVariable(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Logout Successfully");
		long i = userService.updateLoginDetails(userId);
		return new ResponseEntity<>(new RestResponse(i, status), HttpStatus.OK);
	}

}
