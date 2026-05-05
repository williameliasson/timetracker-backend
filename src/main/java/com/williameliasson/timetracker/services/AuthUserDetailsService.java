package com.williameliasson.timetracker.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.williameliasson.timetracker.models.User;
import com.williameliasson.timetracker.repositories.UserRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    public AuthUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (!maybeUser.isPresent()){
            throw new UsernameNotFoundException("Username not found");
        }
        User user = maybeUser.get();

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
    
}
