package com.williameliasson.timetracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.williameliasson.timetracker.dto.CategoryCreationDTO;
import com.williameliasson.timetracker.dto.LoginDTO;
import com.williameliasson.timetracker.models.Category;
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

    public List<Category> getCategoriesByUsername(String username){
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (!maybeUser.isPresent()){
            throw new IllegalArgumentException("User not found");
        }
        User user = maybeUser.get();
        List<Category> categories = user.getCategories();
        return categories;
    }

    public Category createCategory(String username, CategoryCreationDTO dto){
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (!maybeUser.isPresent()){
            throw new IllegalArgumentException("user not found");
        }
        User user = maybeUser.get();

        List<Category> categories = user.getCategories();
        if (categories == null){
            user.setCategories(new ArrayList<>());
            categories = user.getCategories();
        }
        Category category = new Category();
        category.setName(dto.getName());
        category.setId(new ObjectId());
        categories.add(category);
        userRepository.save(user);    
    
        return category;
    }

    public Category getCategoryById(ObjectId categoryId){
        Optional<User> maybeUser = userRepository.findUserByCategoryId(categoryId.toHexString());
        if (!maybeUser.isPresent()){
            throw new IllegalArgumentException("Category does not exist on any user");
        }
        User user = maybeUser.get();
        Category foundCategory = null;
        for (Category category : user.getCategories()){
            if (category.getId().equals(categoryId)) {
                foundCategory = category;
            }
        }
        if (foundCategory == null){
            throw new IllegalArgumentException("Category not found within user");
        }

        return foundCategory;
    }
}
