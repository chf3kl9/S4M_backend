package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue
    int id;
    @Column(unique = true)
    String email;
    boolean isAdmin;

    @OneToMany
    List<Comment> comments;
    @OneToMany
    List<Rating> ratings;
    @ManyToMany
    List<Movie> watchedMovies;
    @ManyToMany
    List<Movie> favorites;

    public User(@JsonProperty("email") String email,
                @JsonProperty("admin") boolean isAdmin) {
        this.email = email;
        this.isAdmin = isAdmin;
    }
}
