package com.williameliasson.timetracker.dto;

public class UserSummaryDTO {
    private Long totalSeconds;
    private String username;
    private String userId;
    
    public UserSummaryDTO(){

    }

    public Long getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(Long totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
