package com.dev.myspace.model.request;

import lombok.NonNull;

import java.util.Set;

import com.dev.myspace.model.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpaceRequest {
	
//	@NonNull
//	private Long userId;
    
    @NonNull
    private String spaceName;
    
    private Set<Users> spaceUsers;	

}
