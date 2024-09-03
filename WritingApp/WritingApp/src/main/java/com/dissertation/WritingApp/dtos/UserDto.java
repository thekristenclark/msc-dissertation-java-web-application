// creates User DTO

package com.dissertation.WritingApp.dtos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class UserDto {

	private @MongoId ObjectId userId;
	private String username;
	private String password;
	private String fullname;
	private String email;
    private Boolean emailVerified; 
	private String verificationToken;
	
	 public UserDto(ObjectId userId, String username, String password, String fullname, String email, Boolean emailVerified, String verificationToken) {
		  super();
		  this.userId = userId;
		  this.username = username;
		  this.password = password;
		  this.fullname = fullname;
		  this.email = email;
		  this.emailVerified = emailVerified;
		  this.verificationToken = verificationToken;
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
	 
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    public Boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	@Override
    public String toString() {
     return "UserDto [username=" + username + ", password=" + password + "]";
    }
}
