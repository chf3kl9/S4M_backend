package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {
    UUID id;
    String name;
    String description;
    String link;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("link") String link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
