package com.dissertation.WritingApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.dissertation.WritingApp.service.MongoAuthUserDetailService;

@SpringBootTest
class WritingAppApplicationTests {

	@Test
	void contextLoads() {
	}

    private MongoAuthUserDetailService userDetailService = null;

    @Autowired
    public WritingAppApplicationTests(MongoAuthUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    public void testMethod() {
        UserDetails user = userDetailService.loadUserByUsername("testUser");
        System.out.println(user);
    }
    
    
	
	
}
