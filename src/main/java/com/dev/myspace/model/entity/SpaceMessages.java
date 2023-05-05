package com.dev.myspace.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SpaceMessages {
	
	 @Id
	 @GeneratedValue(generator = "UUID")
	 @Column
	 // UUID
	 private Long messageId;
	 
	 @Column (name = "space_id")
	 private Long spaceId;
	 
	 @Column (name = "user_id")
	 private Long userId;
	 
	 @Column
	 private String message;
	 
	
//	 @OneToOne(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "space_id", referencedColumnName = "space_id", insertable = false, updatable = false)
//	 @JsonIgnore
//	 private Space space;

}
