package com.dev.myspace.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.myspace.model.entity.Space;
import lombok.NonNull;

public interface SpaceRepository extends JpaRepository<Space, Long>{
		
	List<Space> findByUserId(@NonNull Long userId);
	
	Space findBySpaceId(@NonNull Long userId);
	
}
