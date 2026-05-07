package com.williameliasson.timetracker.dto;

import java.time.Instant;

public class SessionPatchDTO {
    Instant endTime;
    String categoryId;

    public SessionPatchDTO(){

    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
}
