package com.dissertation.WritingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dissertation.WritingApp.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
