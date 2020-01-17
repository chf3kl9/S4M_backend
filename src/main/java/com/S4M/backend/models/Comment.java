package com.S4M.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String text;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    User user;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    Movie movie;

    public Comment(String text, User user, Movie movie) {
        this.text = text;
        this.user = user;
        this.movie = movie;
        movie.getComments().add(this);
        user.getComments().add(this);
    }
}
