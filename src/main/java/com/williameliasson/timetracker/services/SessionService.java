package com.williameliasson.timetracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
