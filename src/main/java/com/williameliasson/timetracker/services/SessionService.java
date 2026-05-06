package com.williameliasson.timetracker.services;

import com.williameliasson.timetracker.repositories.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.williameliasson.timetracker.dto.SessionCreationDTO;
import com.williameliasson.timetracker.models.Session;
import com.williameliasson.timetracker.models.User;
import com.williameliasson.timetracker.repositories.SessionRepository;

@Service
public class SessionService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    
    public SessionService(SessionRepository sessionRepository, UserRepository userRepository, UserService userService){
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Session> findAll(){
        List<Session> sessions = sessionRepository.findAll();
        return sessions;
    }

    public Optional<Session> findById(String id){
        return sessionRepository.findById(id);
    }

    public Session create(SessionCreationDTO sessionCreationDTO, String username){
        if (sessionCreationDTO.getCategoryId().isBlank()){
            throw new IllegalArgumentException("Category ID cannot be blank");
        }
        if (sessionCreationDTO.getStartTime() == null){
            throw new IllegalArgumentException("Start time cannot be blank");
        }
        Optional<User> maybeUser = userRepository.findByUsername(username); 
        if (!maybeUser.isPresent()){
            throw new IllegalArgumentException("user not found");
        }
        User user = maybeUser.get();

        // add some connection to User here
        Session session = new Session();
        session.setCategoryId(new ObjectId(sessionCreationDTO.getCategoryId()));
        session.setStartTime(sessionCreationDTO.getStartTime());
        session.setUserId(new ObjectId(user.getId()));
        session.setEndTime(null);

        return sessionRepository.save(session);

    }

    public List<Session> getAllByUsername(String username){
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (!maybeUser.isPresent()){
            throw new IllegalArgumentException("User not found");
        }
        User user = maybeUser.get();

        return sessionRepository.findByUserId(new ObjectId(user.getId()));
    }

    public Session closeSessionById(String sessionId, Instant endTime, String username){
        // Get the session
        Optional<Session> maybeSession = findById(sessionId);
        if (!maybeSession.isPresent()){
            throw new IllegalArgumentException("Session not found");
        }
        Session session = maybeSession.get();
        
        // check if user owns that session
        Optional<User> maybeUser = userRepository.findByUsername(username);
         if (!maybeUser.isPresent()){
            throw new IllegalArgumentException("User not found");
        }
        User user = maybeUser.get();
        if (!new ObjectId(user.getId()).equals(session.getUserId())){
            throw new IllegalArgumentException("User does not own session");
        }

        // Set new endtime
        session.setEndTime(endTime);

        // save
        sessionRepository.save(session);
        return session;
    }
    

}
