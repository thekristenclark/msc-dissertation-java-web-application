package com.dissertation.WritingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dissertation.WritingApp.models.User;
import com.dissertation.WritingApp.models.Editor;
import com.dissertation.WritingApp.repositories.EditorRepository;
import com.dissertation.WritingApp.repositories.UserRepository;


@SpringBootApplication
public class WritingApplication implements CommandLineRunner{

	private final UserRepository userRepository;
	private final EditorRepository editorRepository;
	
	@Autowired
	public WritingApplication(UserRepository userRepository, EditorRepository editorRepository) {
		this.userRepository = userRepository;
		this.editorRepository = editorRepository;
	}
		
	public static void main(String[] args) {
	        SpringApplication.run(WritingApplication.class, args); // if there is no exception runs the server 
	    } 

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findAll().isEmpty()){
			userRepository.save(new User("Alice", "Smith"));
			userRepository.save(new User("Bob", "Jones"));
		}
		for(User user : userRepository.findAll()) {
			System.out.println(user);
		}
	
		if (editorRepository.findAll().isEmpty()) {
			editorRepository.save(new Editor("Kristen", "yeah, come on it will be fun", "We'll go lots of places and go drinking", "2024-07-19"));
			editorRepository.save(new Editor("Manning", "Don't go", "Kids...SMH", "2024-07-20"));
			editorRepository.save(new Editor("Robert", "Go visit Kristen in Scotland", "I  want to see my castle", "2024-07-18"));
		}
	}
}
