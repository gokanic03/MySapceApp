package com.dev.myspace.serviceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.dev.myspace.model.entity.Space;
import com.dev.myspace.model.entity.SpaceMessages;
import com.dev.myspace.model.entity.Users;
import com.dev.myspace.model.request.SpaceRequest;
import com.dev.myspace.repository.SpaceMessagesRepository;
import com.dev.myspace.repository.SpaceRepository;
import com.dev.myspace.repository.UserRepository;
import com.dev.myspace.services.SpaceServices;

import lombok.NonNull;


@Service
public class SpaceServicesImpl implements SpaceServices{
	
	private SpaceRepository spaceRepository;
	private UserRepository userRepository;
	private SpaceMessagesRepository spaceMessagesRepository;
	
	public SpaceServicesImpl(SpaceRepository spaceRepository, UserRepository userRepository,
		SpaceMessagesRepository spaceMessagesRepository) {
		this.spaceRepository = spaceRepository;
		this.userRepository = userRepository;
		this.spaceMessagesRepository = spaceMessagesRepository;
	}

	@Override
	public List<Space> userSpaceList (Long userId) {		
		List<Space> userSpaces = spaceRepository.findAll();
		return userSpaces;
	}

	@Override
	@Transactional
	public Space createSpace(SpaceRequest spaceRequest, Long userId) {
		
		Users users = userRepository.findById(userId)
				.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
		
		
		Space myspace = new Space(spaceRequest);
		myspace.setUserId(userId);
		myspace.setSpaceUsers(Set.of(users));
		Space savedSpace = spaceRepository.save(myspace);
		
//		users.getJoinedSpaces().add(savedSpace);
		
		
//		savedSpace.getSpaceUsers()
//			.addAll(savedSpace.getSpaceUsers()
//					.stream()
//					.map(u -> {
//						Users users = userRepository.findById(userId)
//								.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
//						users.getJoinedSpaces().add(savedSpace);
//						return users;
//					}).collect(Collectors.toSet()));
		return savedSpace;
	}
	

	@Override
	public SpaceMessages createMessage(Long userId, Long spaceId, String message) {
		//check if user is part of this space.
		Users user = userRepository.findById(userId)
				.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
		Collection<Space> spaces = user.getJoinedSpaces();
		
		boolean match = spaces.stream().anyMatch(p -> p.getSpaceId().equals(spaceId));
		
		//if user has access to space
		if(match) {
			SpaceMessages newMessage = new SpaceMessages();
			newMessage.setSpaceId(spaceId);
			newMessage.setUserId(userId);
			newMessage.setMessage(message);
			SpaceMessages savedMessage = spaceMessagesRepository.save(newMessage);
			return savedMessage;
		}
		else {
			return null;
		}
	}
	
	@Override
	public List<SpaceMessages> spaceMessages(@NonNull Long spaceId) {
		List<SpaceMessages> messages = spaceMessagesRepository.findBySpaceId(spaceId);
		return messages;
	}

	@Override
	public List<SpaceMessages> deleteMessage(Long messageId, Long userId, Long spaceId) {
		SpaceMessages message = spaceMessagesRepository.findById(messageId)
				.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
		if (userId.equals(message.getUserId())) {
			spaceMessagesRepository.deleteById(messageId);
		}
		else {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unautorized user access");
		}
		List<SpaceMessages> messageList = spaceMessagesRepository.findBySpaceId(spaceId);
		return messageList;
	}

}