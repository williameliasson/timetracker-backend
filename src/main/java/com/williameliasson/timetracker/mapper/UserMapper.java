package com.williameliasson.timetracker.mapper;

import com.williameliasson.timetracker.dto.UserDisplayDTO;
import com.williameliasson.timetracker.models.User;

public class UserMapper {
    public static UserDisplayDTO toDisplayDto(User user){
        UserDisplayDTO dto = new UserDisplayDTO();
        dto.setUsername(user.getUsername());
        return dto;
    }
}
