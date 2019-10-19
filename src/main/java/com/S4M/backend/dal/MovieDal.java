package com.S4M.backend.dal;

import com.S4M.backend.model.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieDal {

    int insertMovie(UUID id, Movie movie);

    default int insertMovie(Movie movie) {
        UUID id = UUID.randomUUID();
        return insertMovie(id, movie);
    }

    List<Movie> selectAllMovies();

    Optional<Movie> selectMovieById(UUID id);

    int deleteMovieById(UUID id);

    int updateMovieById(UUID id, Movie movie);
}
