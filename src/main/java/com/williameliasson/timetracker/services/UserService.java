package com.williameliasson.timetracker.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.williameliasson.timetracker.dto.LoginDTO;
import com.williameliasson.timetracker.models.Role;
import com.williameliasson.timetracker.models.User;
import com.williameliasson.timetracker.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        Set<Role> roles = Set.of(Role.ROLE_USER);
        String encodedPassword = passwordEncoder.encode(loginDTO.getPassword());
        
        user.setUsername(loginDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        
        return userRepository.save(user);
    }
}
