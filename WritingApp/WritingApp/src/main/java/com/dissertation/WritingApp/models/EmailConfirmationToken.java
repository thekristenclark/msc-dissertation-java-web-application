// entity that stores the email confirmation token

package com.dissertation.WritingApp.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dissertation.WritingApp.domain.User;


//@Data
@Document
public class EmailConfirmationToken {
	@Id
    private String id;
    @Indexed
    private String token;

	@CreatedDate
    @ReadOnlyProperty
    private LocalDateTime timeStamp;
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;


	@DBRef
    private User user;
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
    public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

    public void setExpiresAt(LocalDateTime createdAt) {
        this.expiresAt = createdAt.plusHours(24);
    }

    // Getter for expiresAt
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    
	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}
	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}



}


