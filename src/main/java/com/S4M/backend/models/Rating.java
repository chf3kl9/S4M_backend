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
@Table(uniqueConstraints ={
        @UniqueConstraint(columnNames = {"user_id", "movie_id"})
})
public class Rating {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int value;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    Movie ratedMovie;

    public Rating(int value, User user, Movie ratedMovie) {
        this.value = value;
        this.user = user;
        this.ratedMovie = ratedMovie;
    }
}
