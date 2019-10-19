package com.S4M.backend.dal;

import com.S4M.backend.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDal")
public class FakeMovieAccessDataService implements MovieDal {

    private static List<Movie> DB = new ArrayList<>();

    @Override
    public int insertMovie(UUID id, Movie movie) {
        DB.add(new Movie(id, movie.getName(), movie.getDescription(), movie.getLink()));
        return 1;
    }

    @Override
    public List<Movie> selectAllMovies() {
        return DB;
    }

    @Override
    public Optional<Movie> selectMovieById(UUID id) {
        return DB.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteMovieById(UUID id) {
        Optional<Movie> movieMaybe = selectMovieById(id);
        if (movieMaybe.isPresent())
        {
            DB.remove(movieMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateMovieById(UUID id, Movie update) {
        return selectMovieById(id)
                .map(movie -> {
                    int index = DB.indexOf(movie);
                    if (index >= 0) {
                        DB.set(index, new Movie(id, update.getName(), update.getDescription(), update.getLink()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


}
