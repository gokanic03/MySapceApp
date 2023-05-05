package com.dev.myspace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.myspace.model.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	List<Users> findByUserId (Long userId);

}
