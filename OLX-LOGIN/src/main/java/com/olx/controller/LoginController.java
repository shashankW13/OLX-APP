package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.olx.dto.Users;
import com.olx.service.LoginService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/olx/login")
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping(value = "/user/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	public String authenticate(@RequestBody Users users) {
		return loginService.authenticate(users);
	}
	
	@DeleteMapping(value = "/user/logout")
	public boolean logout(@RequestHeader ("Authorization") String authToken) {
		return loginService.logout(authToken);
	}
	
	@PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
		public Users registerNewUser(@RequestBody Users users) {
			return loginService.registerUser(users);
	}
	
	@GetMapping(value="/user",produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Reads particular user", notes = "This REST API returns all Users")
	public ResponseEntity<Users> getUserDetail(@RequestHeader ("Authorization") String authToken){
		return new ResponseEntity<>(loginService.getUserDetail(authToken), HttpStatus.OK);
	}
	
	@GetMapping(value="/user/all",produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Reads all users", notes = "This REST API returns all Users")
	public List<Users> getAllUsersDetails(@RequestHeader ("Authorization") String authToken){
		return loginService.getAllUsers(authToken);
	}
	
	
	@GetMapping(value="/user/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Reads users by id", notes = "This REST API returns Users by id")
	public Users userById(@RequestHeader ("Authorization") String authToken,
			@PathVariable("id") int id){
		return loginService.getUserById(authToken, id);
	}
	
	@GetMapping(value="/user/username",produces = {MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Reads users by username", notes = "This REST API returns Users by username")
	public Users userByUsername(@RequestHeader ("Authorization") String authToken){
		return loginService.getUserByUsername(authToken);
	}
	
	@GetMapping(value = "/token/validate")
	public ResponseEntity<Boolean> validateJWT (@RequestHeader("Authorization") String authToken){
		return new ResponseEntity<>(loginService.validate(authToken), HttpStatus.OK);
	}
	
}
