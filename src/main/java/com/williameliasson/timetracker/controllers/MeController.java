package com.williameliasson.timetracker.controllers;

import com.williameliasson.timetracker.services.SessionService;
import org.springframework.web.bind.annotation.RestController;

import com.williameliasson.timetracker.dto.CategoryCreationDTO;
import com.williameliasson.timetracker.dto.CategoryPatchDTO;
import com.williameliasson.timetracker.dto.SessionCreationDTO;
import com.williameliasson.timetracker.dto.SessionDisplayDTO;
import com.williameliasson.timetracker.dto.SessionPatchDTO;
import com.williameliasson.timetracker.mapper.SessionMapper;
import com.williameliasson.timetracker.models.Category;
import com.williameliasson.timetracker.models.Session;
import com.williameliasson.timetracker.services.UserService;

import java.security.Principal;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/me")
public class MeController {

    private final SessionMapper sessionMapper;
    private final SessionService sessionService;
    private final UserService userService;

    public MeController(UserService userService, SessionService sessionService, SessionMapper sessionMapper){
        this.userService = userService;
        this.sessionService = sessionService;
        this.sessionMapper = sessionMapper;
    }

    @GetMapping("/categories")
    public List<Category> getMyCategories(Principal principal) {
        List<Category> categories = userService.getCategoriesByUsername(principal.getName());

        return categories;
    }

    @PostMapping("/categories")
    public Category postMyCategory(Principal principal, @RequestBody CategoryCreationDTO dto) {
        String username = principal.getName();
        Category category = userService.createCategory(username, dto);
        return category;
    }

    @PatchMapping("/categories/{id}")
    public Category patchMyCategory(@PathVariable("id") String id, Principal principal, @RequestBody CategoryPatchDTO dto){
        return userService.changeCategoryNameById(id, dto.getName(), principal.getName());
    }

    @GetMapping("/sessions")
    public List<SessionDisplayDTO> getMySessions(Principal principal) {
        String username = principal.getName();
        return sessionService.getAllByUsername(username).stream()
                .map(sessionMapper::toDisplayDTO)
                .toList();
    }

    @PostMapping("/sessions")
    public SessionDisplayDTO postMySession(@RequestBody SessionCreationDTO sessionCreationDTO, Principal principal) {
        Session session = sessionService.create(sessionCreationDTO, principal.getName());
        return sessionMapper.toDisplayDTO(session);
    }

    @PatchMapping("/sessions/{id}")
    public Session patchMySession(@PathVariable("id") String sessionId, @RequestBody SessionPatchDTO dto, Principal principal){
        return sessionService.patchSessionById(sessionId, dto, principal.getName());
    }

    
}
