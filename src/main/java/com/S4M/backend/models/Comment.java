package com.S4M.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue
    int id;
    String text;

    @ManyToOne
    User user;

    @ManyToOne
    Movie movie;

    public Comment(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
