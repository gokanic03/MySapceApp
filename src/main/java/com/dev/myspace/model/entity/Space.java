package com.dev.myspace.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dev.myspace.model.request.SpaceRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "space")
public class Space {
	
	 @Id
	 @GeneratedValue(generator = "UUID")
	 @Column (name = "space_id")
	 private Long spaceId;
	 
	 @Column (name = "user_id")
	 private Long userId;
	 
	 @Column
	 private String spaceName;
	 
	 @ManyToMany(mappedBy = "joinedSpaces")
//	 @JsonIgnore
	 Set<Users> spaceUsers = new HashSet<>();
//	 Collection<Users> spaceUsers = new ArrayList<>();
	 
 
	 public Space (SpaceRequest spaceRequest) {
			this(null, // spaceId
					null,
					spaceRequest.getSpaceName(),
					null
			);
		}
}
