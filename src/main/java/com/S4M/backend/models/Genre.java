package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Genre {

    @Id @GeneratedValue
    int id;
    String name;

    @ManyToMany
    List<Movie> movies;

    public Genre(@JsonProperty("id") int id,
                 @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
