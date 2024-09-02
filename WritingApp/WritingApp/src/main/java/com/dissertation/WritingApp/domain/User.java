// creates a user entity

package com.dissertation.WritingApp.domain;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class User {
    private @MongoId ObjectId userId;
    private String username;
    private String password;
    private Set<UserRole> userRoles;
    private String fullname;
    private String email;
    private boolean enabled = false; // To track email confirmation
    
    public User(ObjectId userId, String username, String password, String fullname, String email) {
    	super();
    	this.userId = userId;
    	this.username = username;
    	this.password = password;
    	this.fullname = fullname;
    	this.email = email;

    }

	public ObjectId getUserId() {
        return userId;
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
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
    public String toString() {
     return "User [id=" + userId + ", username=" + username + ", password=" + password + "]";
    }
    
}