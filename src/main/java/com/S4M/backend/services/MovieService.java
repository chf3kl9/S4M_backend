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
        Movie existing = repository.findById(movie.getId()).get();
        copyNonNullProperties(movie, existing);
        return repository.save(movie);
    }

    void copyNonNullProperties(Movie updated, Movie original){
        updated.setGenres(Optional.ofNullable(updated.getGenres()).orElse(original.getGenres()));
        updated.setComments(Optional.ofNullable(updated.getComments()).orElse(original.getComments()));
        updated.setRatings(Optional.ofNullable(updated.getRatings()).orElse(original.getRatings()));
        updated.setImageURL(Optional.ofNullable(updated.getImageURL()).orElse(original.getImageURL()));
    }
}
