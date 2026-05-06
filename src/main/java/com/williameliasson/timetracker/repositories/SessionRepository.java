package com.williameliasson.timetracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.williameliasson.timetracker.models.Session;
import org.bson.types.ObjectId;


public interface SessionRepository extends MongoRepository<Session, String>{
    Optional<Session> findById(String id);

    List<Session> findAll();

    List<Session> findByUserId(ObjectId userId);
}
