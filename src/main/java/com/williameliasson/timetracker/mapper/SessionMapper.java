package com.williameliasson.timetracker.mapper;

import com.williameliasson.timetracker.dto.SessionDisplayDTO;
import com.williameliasson.timetracker.models.Session;

public class SessionMapper {
    public static SessionDisplayDTO toDisplayDTO(Session session){
        SessionDisplayDTO dto = new SessionDisplayDTO();
        dto.setCategory("TEST");
        dto.setStartTime(session.getStartTime());
        dto.setEndTime(session.getEndTime());
        dto.setId(session.getId());
        return dto;
    }
}
