package com.williameliasson.timetracker.models;

import org.bson.types.ObjectId;

import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

public class Category {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String name;

    public Category(){

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
