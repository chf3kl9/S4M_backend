package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre {

    @Id @GeneratedValue
    int id;

    @Column(unique = true)
    String name;

    @ManyToMany
    List<Movie> movies;

    public Genre(@JsonProperty("id") int id,
                 @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
