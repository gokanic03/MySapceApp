//package com.dev.myspace.model.entity;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@IdClass(SpaceUsersId.class)
//public class SpaceUsers {
//	
//	@Id
//	@Column (name = "user_id")
//	private Long userId;
//	
//	@Id
//	@Column (name = "space_id")
//	private Long spaceId;
//
//	 @OneToOne(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
//	 private Users users;
//	
//	 @OneToOne(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "space_id", referencedColumnName = "space_id", insertable = false, updatable = false)
//	 private Space space;
//
//
//}
