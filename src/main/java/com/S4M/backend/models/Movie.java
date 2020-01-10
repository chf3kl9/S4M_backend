package com.S4M.backend.models;

import com.fasterxml.jackson.annotation.*;
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

    public Movie(@JsonProperty("title") String title,
                 @JsonProperty("description") String description,
                 @JsonProperty("link") String link,
                 @JsonProperty("imageURL") String imageURL) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.imageURL = imageURL;
    }

}
