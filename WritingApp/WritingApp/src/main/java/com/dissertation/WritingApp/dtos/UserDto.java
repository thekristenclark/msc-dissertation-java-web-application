// creates User DTO

package com.dissertation.WritingApp.dtos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class UserDto {

	private @MongoId ObjectId userId;
	private String username;
	private String password;
	private String fullname;
	
	 public UserDto(ObjectId userId, String username, String password, String fullname) {
		  super();
		  this.userId = userId;
		  this.username = username;
		  this.password = password;
		  this.fullname = fullname;
	 }
	
	public ObjectId getUserId() {
		return userId;
	}
	 
	public String getUsername() {
		 return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	 
    @Override
    public String toString() {
     return "UserDto [username=" + username + ", password=" + password + "]";
    }
}
