package com.S4M.backend.service;

import com.S4M.backend.dal.MovieDal;
import com.S4M.backend.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieDal movieDal;

    @Autowired
    public MovieService(@Qualifier("fakeDal") MovieDal movieDal) {
        this.movieDal = movieDal;
    }

    public int insertMovie(Movie movie) {
        return movieDal.insertMovie(movie);
    }

    public List<Movie> getAllMovies() {
        return movieDal.selectAllMovies();
    }

    public Optional<Movie> getMovieById(UUID id) {
        return movieDal.selectMovieById(id);
    }

    public int deleteMovieById(UUID id) {
        return movieDal.deleteMovieById(id);
    }

    public int updateMovieById(UUID id, Movie movie) {
        return movieDal.updateMovieById(id, movie);
    }
}
