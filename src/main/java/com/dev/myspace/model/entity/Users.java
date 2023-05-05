package com.dev.myspace.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dev.myspace.model.request.UserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
		
	 @Id
	 @GeneratedValue(generator = "UUID")
	 @Column (name = "user_id")
	 private Long userId;
	 @Column
	 private String userName;
	 @Column
	 private String password;
	 @Column
	 private String fullName;
	 @Column
	 private String dob;
	 
	 @EqualsAndHashCode.Exclude
	 @ManyToMany (fetch = FetchType.EAGER)
	 @JoinTable(name= "user_space", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="space_id"))
	 @JsonIgnore
//	 Collection<Space> joinedSpaces =  new ArrayList<>();
	 Set<Space> joinedSpaces = new HashSet<>();
	 
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	 private Collection<SpaceMessages> messages;
	 
	 public Users setJoinedSpaces(final Set<Space> joinedSpaces) {
	     this.joinedSpaces.clear();
	     if (joinedSpaces != null) {
	         this.joinedSpaces.addAll(joinedSpaces);
	     }
	     return this;
	 }
	 
	 public Users (UserRequest userRequest) {
			this(null, // userId
					userRequest.getUserName(), 
					userRequest.getPassword(), 
					userRequest.getFullName(),
					userRequest.getDob(),
					null
					,null
			);
		}
}
