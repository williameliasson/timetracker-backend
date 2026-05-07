package com.williameliasson.timetracker.dto;

public class UserSummaryDTO {
    private Integer totalSeconds;
    private String username;
    private String userId;
    
    public UserSummaryDTO(){

    }

    public Integer getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(Integer totalSeconds) {
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
