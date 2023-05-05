package com.dev.myspace.services;

import java.util.List;

import com.dev.myspace.model.entity.Space;
import com.dev.myspace.model.entity.SpaceMessages;
import com.dev.myspace.model.request.SpaceRequest;

import lombok.NonNull;

public interface SpaceServices {
	
	//Create User Space
	Space createSpace (@NonNull SpaceRequest spaceRequest, Long userId);
	
	//Join Space
//	Space joinSpace (Long SpaceId, Long userId);
	
	//Retrieve User spaces list
	List<Space> userSpaceList (@NonNull Long userId);
	
	List<SpaceMessages> spaceMessages (@NonNull Long spaceId);
	
	//Send a Space messages
	SpaceMessages createMessage (Long userId, Long spaceId, String message);
	
	//Send a Space messages
	List<SpaceMessages> deleteMessage (Long messageId, Long userId, Long spaceId);

}
