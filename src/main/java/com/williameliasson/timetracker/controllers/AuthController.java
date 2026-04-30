package com.williameliasson.timetracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.williameliasson.timetracker.dto.LoginDTO;
import com.williameliasson.timetracker.dto.UserDisplayDTO;
import com.williameliasson.timetracker.mapper.UserMapper;
import com.williameliasson.timetracker.models.User;
import com.williameliasson.timetracker.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {
    
    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/auth/register")
    public UserDisplayDTO postMethodName(@RequestBody LoginDTO loginDTO) {
        User user = userService.registerUser(loginDTO);
        
        return UserMapper.toDisplayDto(user);
    }
        
}
