// creates a user entity

package com.dissertation.WritingApp.domain;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class User {
    private @MongoId ObjectId id;
    private String username;
    private String password;
    private Set<UserRole> userRoles;
    private String fullname;
    
    public User(String username, String password, String fullname) {
    	super();
    	this.username = username;
    	this.password = password;
    	this.fullname = fullname;

    }

	public ObjectId getId() {
        return id;
    }

 //   @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

//    @Override
    public Set<UserRole> getAuthorities() {
        return this.userRoles;
    }

//    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
    	return fullname;
	}

    public void setFullname(String fullname) {
    	this.fullname = fullname;
	}

    @Override
    public String toString() {
     return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
    
}