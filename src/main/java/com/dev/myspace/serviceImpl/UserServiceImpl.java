package com.dev.myspace.serviceImpl;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.dev.myspace.model.entity.Space;
import com.dev.myspace.model.entity.Users;
import com.dev.myspace.model.request.UserRequest;
import com.dev.myspace.repository.SpaceRepository;
import com.dev.myspace.repository.UserRepository;
import com.dev.myspace.services.UserServices;

import lombok.NonNull;

@Service
public class UserServiceImpl implements UserServices{
	
	private final UserRepository userRepository;
	private final SpaceRepository spaceRepository;
	
	public UserServiceImpl(UserRepository userRepository, SpaceRepository spaceRepository) {
		this.userRepository = userRepository;
		this.spaceRepository = spaceRepository;
	}

	@Override
	public Users register(@NonNull UserRequest userRequest) {
		Users users = new Users(userRequest);
		Users savedUser = userRepository.save(users);
		return savedUser;
	}
	
	
	public boolean isAuthenticSpaceAdmin (Long adminId, Long temp) {
		
		if (adminId.equals(temp)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	
	@Override
	@Transactional
	public Set<Users> addUser(Long adminId, Long spaceId, Long userId) {
		// TODO Auto-generated method stub
		
		Space space = spaceRepository.findById(spaceId)
				.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Space  Not found!"));
		Long createrId = space.getUserId();
		
		//check if User is Admin
		if (isAuthenticSpaceAdmin(adminId,createrId))
		{	
//			Users users = userRepository.findById(userId)
//					.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
//			
//			space.getSpaceUsers().add(users);
//			users.getJoinedSpaces().add(space);
			Set<Users> spaceUsers = space.getSpaceUsers();
			Users user = userRepository.findById(userId)
					.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
			
//			space.getSpaceUsers().add(user);
			spaceUsers.add(user);
			System.out.println("Inside");
//			spaceUsers.addAll(Set.of(user));
			space.setSpaceUsers(spaceUsers);
			spaceRepository.save(space);
			return spaceUsers;
//			space.getSpaceUsers()
//			.addAll(space.getSpaceUsers()
//					.stream()
//					.map(u -> {
//						Users users = userRepository.findById(userId)
//								.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
//						users.getJoinedSpaces().add(space);
//						return users;
//					}).collect(Collectors.toList()));
		}
		else {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "User not autorized to add users!");
		}
//		return null;
	}

	@Override
	public Collection<Space> userSpaceList(@NonNull Long userId) {
		Users user = userRepository.findById(userId)
				.orElseThrow(()-> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User  Not found!"));
		Collection<Space> spaceList = user.getJoinedSpaces();
//		Collection<Space> spaceList = spaceRepository.findByUserId(userId);
		return spaceList;
	}

}

