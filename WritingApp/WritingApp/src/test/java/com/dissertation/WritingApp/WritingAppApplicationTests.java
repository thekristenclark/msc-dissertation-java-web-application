package com.dissertation.WritingApp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dissertation.WritingApp.domain.User;
import com.dissertation.WritingApp.repositories.UserRepository;
import com.dissertation.WritingApp.service.UserService;


@SpringBootTest
public class WritingAppApplicationTests {

	@Autowired
	private UserRepository userRepository;
		
	private User testUser;
	
	@Test
	void newUserTest() {
        testUser = new User();
        testUser.setUsername("NewUserTest");
        testUser.setPassword("NewUserTest");
        testUser.setEmail("NewUserTest@example.com");
        testUser.setVerificationToken("123456789");
        userRepository.save(testUser);
        
		System.out.println("Test user created: " + testUser);
		
        User savedUser = userRepository.findUserByUsername("NewUserTest");
        System.out.println("Finding user by username 'testUser': " + savedUser);
	}
    

    @Test
    public void testFindUserByUsernameNotFound() {
        User foundUser = userRepository.findUserByUsername("nonexistentuser");
        assertThat(foundUser).isNull();
    }

    @Test
    public void testFindByEmailNotFound() {
        User foundUser = userRepository.findByEmail("nonexistent@example.com");
        assertThat(foundUser).isNull();
    }

    @Test
    public void testFindByVerificationTokenNotFound() {
        User foundUser = userRepository.findByVerificationToken("nonexistenttoken");
        assertThat(foundUser).isNull();
    }
    
}
