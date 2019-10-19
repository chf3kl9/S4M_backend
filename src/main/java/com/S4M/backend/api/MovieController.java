package com.S4M.backend.api;

import com.S4M.backend.model.Movie;
import com.S4M.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/movie")
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
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(path = "{id}")
    public Movie getMovieById(@PathVariable("id") UUID id) {
        return movieService.getMovieById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMovieById(@PathVariable("id") UUID id) {
        movieService.deleteMovieById(id);
    }

    @PutMapping(path = "{id}")
    public void updateMovieById(@PathVariable("id") UUID id, @RequestBody Movie movie) {
        movieService.updateMovieById(id, movie);
    }
}
