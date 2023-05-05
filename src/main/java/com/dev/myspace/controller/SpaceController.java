package com.dev.myspace.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.myspace.model.entity.Space;
import com.dev.myspace.model.entity.SpaceMessages;
import com.dev.myspace.model.request.SpaceRequest;
import com.dev.myspace.services.SpaceServices;

import lombok.NonNull;

@RestController
@RequestMapping("/app/space")
public class SpaceController {
	
	private final SpaceServices spaceServices;

	//Constructor based dependency injection
	public SpaceController(SpaceServices spaceServices) {
		this.spaceServices = spaceServices;
	}
	
	// Create Space
	@PostMapping(path = "/Create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createSpaces(@RequestHeader("userId") Long userId, @RequestBody final @NonNull SpaceRequest spaceRequest) {
		Space mySpace = spaceServices.createSpace(spaceRequest, userId);
	    return ResponseEntity.ok(mySpace);
	}
	
//	/space/{spaceId}/join
//	@PostMapping(path = "{spaceId}/AddUser", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<?> joinSpace(@RequestHeader("userId") Long userId, @RequestBody final @NonNull Long spaceId) {
//		Space joinedSpace= spaceServices.addSpaceUser(spaceId, userId);
//	    return ResponseEntity.ok(joinedSpace);
//	}

	//User Space List
	@PostMapping(path = "/UserSpaceList", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> userSpaceList(@RequestHeader("userId") Long userId) {
	    List<Space> userSpaces = spaceServices.userSpaceList(userId);
	    return ResponseEntity.ok(userSpaces);
	}
	
	//Space Messages
	@PostMapping(path = "/Messages", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> messageList(@RequestHeader("userId") Long userId , @RequestHeader("spaceId") Long spaceId) {
	    List<SpaceMessages> allMessages = spaceServices.spaceMessages(spaceId);
	    return ResponseEntity.ok(allMessages);
	}
	
	//Send Message
	@PostMapping(path = "{spaceId}/SendMessage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createMessage(@RequestHeader("userId") Long userId , @PathVariable Long spaceId, @RequestBody String message) {
	    SpaceMessages newMessage = spaceServices.createMessage(userId, spaceId, message);
	    return ResponseEntity.ok(newMessage);
	}
	
	//Delete Message
	@PostMapping(path = "{spaceId}/{messageId}/DeleteMessage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createMessage(@RequestHeader("userId") Long userId	, @PathVariable Long spaceId, @PathVariable Long messageId) {
	    List<SpaceMessages> messages = spaceServices.deleteMessage(messageId, userId, spaceId);
	    return ResponseEntity.ok(messages);
	}

}
