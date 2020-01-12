package com.S4M.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Rating> ratings;
    @ManyToMany
    List<Movie> watchedMovies;
    @ManyToMany
    List<Movie> favorites;

    public User(String email, boolean isAdmin) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.comments = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.favorites = new ArrayList<>();
    }
}
