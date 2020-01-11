package com.S4M.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id @GeneratedValue
    int id;
    String title;
    String description;
    String link;
    String imageURL;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    List<Genre> genres;
    @OneToMany
    List<Comment> comments;
    @OneToMany
    List<Rating> ratings;

    public Movie(String title, String description, String link, String imageURL) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.imageURL = imageURL;
    }

}
