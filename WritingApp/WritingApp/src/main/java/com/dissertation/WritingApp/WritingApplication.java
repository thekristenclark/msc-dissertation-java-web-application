package com.dissertation.WritingApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.dissertation.WritingApp")
@EnableMongoRepositories(basePackages = "com.dissertation.WritingApp.repositories")
public class WritingApplication implements CommandLineRunner{
		
	public static void main(String[] args) {
	        SpringApplication.run(WritingApplication.class, args); // if there is no exception runs the server 
	    } 

	@Override
	public void run(String... args) throws Exception {		
	}
}
