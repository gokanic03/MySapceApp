package com.dev.myspace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.myspace.model.entity.SpaceMessages;

public interface SpaceMessagesRepository extends JpaRepository<SpaceMessages, Long>{
	
	List<SpaceMessages> findBySpaceId (Long spaceId);

}
