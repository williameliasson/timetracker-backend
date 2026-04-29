package com.williameliasson.timetracker.models;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sessions")
public class Session {
    @Id
    private String id;
    private Instant startTime;
    private Instant endTime;

    private ObjectId categoryId;

    public Session(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public ObjectId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ObjectId categoryId) {
        this.categoryId = categoryId;
    }
    

}
