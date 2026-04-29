package com.williameliasson.timetracker.dto;

import java.time.Instant;

public class SessionCreationDTO {
    private Instant startTime;

    private String categoryId;

    public SessionCreationDTO(){

    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    
}
