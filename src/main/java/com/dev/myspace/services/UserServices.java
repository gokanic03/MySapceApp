package com.dev.myspace.services;

import java.util.Collection;

import com.dev.myspace.model.entity.Space;
import com.dev.myspace.model.entity.Users;
import com.dev.myspace.model.request.UserRequest;

import lombok.NonNull;

public interface UserServices {
	
	//register Users
	Users register(@NonNull UserRequest userRequest);
	
	//Add Space User
	Collection<Space> addUser (Long adminId, Long SpaceId, Long userId);
	
	//Retrieve User spaces list
	Collection<Space> userSpaceList (@NonNull Long userId);
	
}
