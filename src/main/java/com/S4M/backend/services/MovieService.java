package com.S4M.backend.services;

import com.S4M.backend.models.Movie;
import com.S4M.backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository repository;

    public Movie insertMovie(Movie movie) {
        return repository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Optional<Movie> getMovieById(int id) {
        return repository.findById(id);
    }

    public void deleteMovieById(int id) {
        repository.deleteById(id);
    }

    public Movie updateMovie(Movie movie) {
        return repository.save(movie);
    }
}
