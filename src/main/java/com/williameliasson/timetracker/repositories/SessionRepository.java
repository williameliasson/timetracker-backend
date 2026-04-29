package com.williameliasson.timetracker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.williameliasson.timetracker.models.Session;

public interface SessionRepository extends MongoRepository<Session, String>{
    Optional<Session> findById(String id);

    List<Session> findAll();
}
