package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Movie {

    @Id @GeneratedValue
    int id;
    String name;
    String description;
    String link;

    public Movie(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("link") String link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
    }

}
