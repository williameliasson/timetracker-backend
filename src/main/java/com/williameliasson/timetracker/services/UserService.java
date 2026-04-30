package com.williameliasson.timetracker.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.williameliasson.timetracker.dto.LoginDTO;
import com.williameliasson.timetracker.models.Role;
import com.williameliasson.timetracker.models.User;
import com.williameliasson.timetracker.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(LoginDTO loginDTO){
        Optional<User> existingUser = userRepository.findByUsername(loginDTO.getUsername());
        if (existingUser.isPresent()){
            throw new IllegalArgumentException("Username taken");
        }
        if (loginDTO.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (loginDTO.getPassword().isBlank()){
            throw new IllegalArgumentException("Password cannot be blank");
        }
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
