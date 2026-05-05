package com.williameliasson.timetracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.williameliasson.timetracker.dto.CategoryCreationDTO;
import com.williameliasson.timetracker.models.Category;
import com.williameliasson.timetracker.services.UserService;

import java.security.Principal;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/me")
public class MeController {

    private final UserService userService;

    public MeController(UserService userService){
        this.userService = userService;
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
    
    
}
