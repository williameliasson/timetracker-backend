package com.williameliasson.timetracker.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.williameliasson.timetracker.dto.UserSummaryDTO;
import com.williameliasson.timetracker.models.Session;
import com.williameliasson.timetracker.models.User;

@Service
public class AdminService {
    
    private final SessionService sessionService;
    private final UserService userService;

    public AdminService(UserService userService, SessionService sessionService){
        this.userService = userService;
        this.sessionService = sessionService;
    }

    public List<UserSummaryDTO> getAllUserSummaries(){
        List<UserSummaryDTO> userSummaryDTOs = new ArrayList<>();
        final Integer timeframeDays = 30;
        // get all users
        List<User> users = userService.getAllUsers();
        
        // get their sessions
        for (User user : users){
            List<Session> sessions = sessionService.getAllByUsername(user.getUsername());
            LocalDate cutoffDate = LocalDate.now().minusDays(timeframeDays);
            ZoneId zone = ZoneId.systemDefault();
            List<Session> filteredSessions = sessions.stream()
                .filter(session -> session.getEndTime() != null)
                .filter(session -> LocalDate.ofInstant(session.getStartTime(),zone).isAfter(cutoffDate))
                .toList();
            Long sumSeconds = 0L;
            // sum the duration of each session
            for (Session session : filteredSessions){
                // src https://www.baeldung.com/java-period-duration
                Duration duration = Duration.between(session.getStartTime(), session.getEndTime());
                sumSeconds += duration.toSeconds();
            }
            // combine into DTO
            UserSummaryDTO userSummaryDTO = new UserSummaryDTO();
            userSummaryDTO.setTotalSeconds(sumSeconds);
            userSummaryDTO.setUserId(user.getId());
            userSummaryDTO.setUsername(user.getUsername());
            userSummaryDTOs.add(userSummaryDTO);
        }
        return userSummaryDTOs;
    }
}
