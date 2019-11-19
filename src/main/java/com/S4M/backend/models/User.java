package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    int id;
    String name;
    String password;
    boolean isAdmin;

    @OneToMany
    List<Comment> comments;
    @OneToMany
    List<Rating> ratings;
    @ManyToMany
    List<Movie> watchedMovies;
    @ManyToMany
    List<Movie> favorites;

    public User(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
