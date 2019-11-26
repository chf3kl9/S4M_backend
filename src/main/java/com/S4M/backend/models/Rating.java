package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints ={
        @UniqueConstraint(columnNames = {"user_id", "movie_id"})
})
public class Rating {

    @Id @GeneratedValue
    int id;
    int value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie ratedMovie;

    public Rating(@JsonProperty("id") int id,
                 @JsonProperty("value") int value) {
        this.id = id;
        this.value = value;
    }
}
