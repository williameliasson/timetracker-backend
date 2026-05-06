package com.williameliasson.timetracker.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.williameliasson.timetracker.models.User;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByUsername(String username);
    @Query("{'categories._id' : ?0}")
    Optional<User> findUserByCategoryId(String categoryId);
}
