// repo that interacts with the Mongodb

package com.dissertation.WritingApp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dissertation.WritingApp.models.EmailConfirmationToken;

@Repository
public interface EmailConfirmationTokenRepository extends MongoRepository<EmailConfirmationToken, String>{
    EmailConfirmationToken findByToken(String token);
}
