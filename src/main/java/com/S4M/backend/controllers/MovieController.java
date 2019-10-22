package com.S4M.backend.controllers;

import com.S4M.backend.models.Movie;
import com.S4M.backend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/movies")
@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public void insertMovie(@RequestBody Movie movie) {
        movieService.insertMovie(movie);
    }

    @GetMapping
    public Object getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "{id}")
    public Movie getMovieById(@PathVariable("id") int id) {
        return movieService.getMovieById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMovieById(@PathVariable("id") int id) {
        movieService.deleteMovieById(id);
    }

    @PutMapping
    public void updateMovieById(@RequestBody Movie movie) {
        movieService.updateMovie(movie);
    }
}
