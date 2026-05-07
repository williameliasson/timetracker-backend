package com.williameliasson.timetracker.controllers;

import com.williameliasson.timetracker.dto.UserSummaryDTO;
import com.williameliasson.timetracker.services.SessionService;
import com.williameliasson.timetracker.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final SessionService sessionService;
    private final UserService userService;

    public AdminController(UserService userService, SessionService sessionService){
        this.userService = userService;
        this.sessionService = sessionService;
    }
    @GetMapping("/summaries")
    public List<UserSummaryDTO> getAllUserSummaries() {
        final Integer timeframeDays = 30;
        // get all users
        // get their sessions
        // sum the duration of each session
        // combine into DTO
    }
    
}
