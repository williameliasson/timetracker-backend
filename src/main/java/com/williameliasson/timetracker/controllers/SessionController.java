package com.williameliasson.timetracker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williameliasson.timetracker.dto.SessionCreationDTO;
import com.williameliasson.timetracker.dto.SessionDisplayDTO;
import com.williameliasson.timetracker.mapper.SessionMapper;
import com.williameliasson.timetracker.models.Session;
import com.williameliasson.timetracker.services.SessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private SessionService sessionService;

    public SessionController(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @PostMapping("")
    public SessionDisplayDTO createSession(@RequestBody SessionCreationDTO sessionCreationDTO) {
        Session session = sessionService.create(sessionCreationDTO);
        return SessionMapper.toDisplayDTO(session);
    }
    
}
