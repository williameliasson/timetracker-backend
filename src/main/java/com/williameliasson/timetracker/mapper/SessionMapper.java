package com.williameliasson.timetracker.mapper;

import org.springframework.stereotype.Component;

import com.williameliasson.timetracker.dto.SessionDisplayDTO;
import com.williameliasson.timetracker.models.Category;
import com.williameliasson.timetracker.models.Session;
import com.williameliasson.timetracker.services.UserService;

@Component
public class SessionMapper {
    private final UserService userService;

    public SessionMapper(UserService theUserService){
        userService = theUserService;
    }

    public SessionDisplayDTO toDisplayDTO(Session session){
        SessionDisplayDTO dto = new SessionDisplayDTO();
        Category category = userService.getCategoryById(session.getCategoryId());
        dto.setCategory(category.getName());
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setId(session.getId());
        dto.setCategoryId(session.getCategoryId().toHexString());
        return dto;
    }
}
