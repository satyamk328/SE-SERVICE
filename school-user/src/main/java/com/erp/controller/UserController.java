package com.erp.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.model.User;
import com.erp.service.UserService;
import com.erp.spring.model.RestResponse;
import com.erp.spring.model.RestStatus;

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
	
	@PostMapping(value = "/registerUser")
	public ResponseEntity<RestResponse<Object>> registration(@RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Registered Successfully");
		return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
	}
	

	@PostMapping(value = "/serviceLoginAuth")
	public ResponseEntity<RestResponse<Object>> loginauthentication(@RequestBody(required = true) User user)
			throws UnsupportedEncodingException {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Login Successfully");
		if (user.getEmailId() == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Please enter valid Email/Phone");
			return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			user = userService.loginauthentication(user);
			if (user == null) {
				status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						"Unauthorized User. Please login with your valid credential!");
				return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
	}

	@GetMapping(value = "/changePassword")
	public ResponseEntity<RestResponse<Object>> getUserDetails(
			@RequestParam(name = "email", required = true) String email) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Forgot password Successfully");
		return new ResponseEntity<>(new RestResponse(null, status), HttpStatus.OK);
	}

	@PutMapping(value = "/resetpassword/{uid}")
	public ResponseEntity<RestResponse<Object>> resetPassword(@PathVariable(name = "uid", required = true) String uid,
			@RequestParam(name = "newPassword", required = true) String pass) throws UnsupportedEncodingException {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Forgot change Successfully");
		return new ResponseEntity<>(new RestResponse(true, status), HttpStatus.OK);
	}
	
	@PutMapping(value = "/logout/{uid}")
	public ResponseEntity<RestResponse<Object>> logOut(
			@PathVariable(name = "uid", required = true) String uid,
			@RequestParam(name = "ip", required = false, defaultValue="127.0.0.0") String ip) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Logout Successfully");
		
		return new ResponseEntity<>(new RestResponse(true, status), HttpStatus.OK);
	}

}
