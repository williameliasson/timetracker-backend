package com.williameliasson.timetracker.dto;

import java.time.Instant;

public class SessionPatchDTO {
    Instant endTime;

    public SessionPatchDTO(){

    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
    
}
