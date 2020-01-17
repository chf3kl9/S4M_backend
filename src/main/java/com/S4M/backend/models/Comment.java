package com.S4M.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String text;

    @ManyToOne
    User user;

    @ManyToOne
    Movie movie;

    public Comment(String text, User user, Movie movie) {
        this.text = text;
        this.user = user;
        this.movie = movie;
        movie.getComments().add(this);
        user.getComments().add(this);
    }
}
