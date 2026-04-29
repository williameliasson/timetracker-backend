package com.williameliasson.timetracker.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.williameliasson.timetracker.dto.SessionCreationDTO;
import com.williameliasson.timetracker.models.Session;
import com.williameliasson.timetracker.repositories.SessionRepository;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    
    public SessionService(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAll(){
        List<Session> sessions = sessionRepository.findAll();
        return sessions;
    }

    public Optional<Session> findById(String id){
        return sessionRepository.findById(id);
    }

    public Session create(SessionCreationDTO sessionCreationDTO){
        if (sessionCreationDTO.getCategoryId().isBlank()){
            throw new IllegalArgumentException("Category ID cannot be blank");
        }
        if (sessionCreationDTO.getStartTime() == null){
            throw new IllegalArgumentException("Category ID cannot be blank");
        }

        // add some connection to User here
        Session session = new Session();
        session.setCategoryId(new ObjectId(sessionCreationDTO.getCategoryId()));
        session.setStartTime(sessionCreationDTO.getStartTime());
        session.setEndTime(null);

        return sessionRepository.save(session);

    }

}
