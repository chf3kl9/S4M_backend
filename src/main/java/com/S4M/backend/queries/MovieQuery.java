package com.S4M.backend.queries;

import com.S4M.backend.models.Movie;
import com.S4M.backend.services.MovieService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class MovieQuery implements GraphQLQueryResolver {

    private final MovieService movieService;

    public MovieQuery(MovieService movieService) {
        this.movieService = movieService;
    }


    public List<Movie> movies() {
        return movieService.getAllMovies();
    }

    public Optional<Movie> movie(Integer id) {
        return movieService.getMovieById(id);
    }
}
