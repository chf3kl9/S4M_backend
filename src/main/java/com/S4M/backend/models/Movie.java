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
    String title;
    String description;
    String link;

    public Movie(@JsonProperty("id") int id,
                 @JsonProperty("title") String title,
                 @JsonProperty("description") String description,
                 @JsonProperty("link") String link) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
    }

}
