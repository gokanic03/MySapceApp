package com.dev.myspace.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.myspace.model.entity.Space;
import com.dev.myspace.model.entity.Users;
import com.dev.myspace.model.request.UserRequest;
import com.dev.myspace.services.UserServices;

import lombok.NonNull;


@RestController
@RequestMapping("/app/user")
public class UserController {
	
	private final UserServices userServices;
	
	public UserController(UserServices userServices) {
	        this.userServices = userServices;
	}
	  
	@PostMapping(path = "/Register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody final @NonNull UserRequest userRequest) {
	    Users users = userServices.register(userRequest);
	    
	    return ResponseEntity.ok(users);
	}
	
	@PostMapping(path = "/{spaceId}/AddUser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> registerUser(@RequestHeader("adminId") Long adminId , @PathVariable Long spaceId, @RequestHeader("userId") Long userId) {
		Set<Users> space = userServices.addUser(adminId, spaceId, userId);
	    return ResponseEntity.ok(space);
	}
	
	@PostMapping(path = "/AllSpaces", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> spaceList(@RequestHeader("userId") Long userId) {
		Collection<Space> space = userServices.userSpaceList(userId);
	    return ResponseEntity.ok(space);
	}
	
}
