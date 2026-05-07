package com.williameliasson.timetracker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williameliasson.timetracker.dto.SessionDisplayDTO;
import com.williameliasson.timetracker.mapper.SessionMapper;
import com.williameliasson.timetracker.services.SessionService;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionMapper sessionMapper;
    private SessionService sessionService;

    public SessionController(SessionService sessionService, SessionMapper sessionMapper){
        this.sessionService = sessionService;
        this.sessionMapper = sessionMapper;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SessionDisplayDTO> getAllSessions() {
        return sessionService.findAll().stream()
            .map(sessionMapper::toDisplayDTO)
            .toList();
    }
    
    
}
