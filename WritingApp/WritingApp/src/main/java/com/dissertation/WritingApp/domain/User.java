// creates a user entity

package com.dissertation.WritingApp.domain;

//import java.util.Collection;
//import java.util.Objects;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

//import com.dissertation.WritingApp.repositories.UserRepository;

@Document
//public class User implements UserDetails {
public class User {
    private @MongoId ObjectId id;
    private String username;
    private String password;
    private Set<UserRole> userRoles;
    private String fullname;
      
//    
//    public User(String username, String password, Set<UserRole> userRoles) {
//		super();
//		this.username = username;
//		this.password = password;
//		this.userRoles = userRoles;
//	}

    
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
    
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }

 /*   @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities2() {
        return null;
    }
 */   

    @Override
    public String toString() {
     return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
    
}