package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class Rating {

    @Id @GeneratedValue
    int id;
    int value;

    @ManyToOne
    User user;
    @ManyToOne
    Movie ratedMovie;

    public Rating(@JsonProperty("id") int id,
                 @JsonProperty("value") int value) {
        this.id = id;
        this.value = value;
    }
}
