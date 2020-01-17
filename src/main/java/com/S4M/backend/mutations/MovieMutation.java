package com.S4M.backend.mutations;

import com.S4M.backend.models.Movie;
import com.S4M.backend.services.MovieService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class MovieMutation implements GraphQLMutationResolver {

    private final MovieService movieService;

    public MovieMutation(MovieService movieService) {
        this.movieService = movieService;
    }

    public Movie createMovie(String title, String description, String link, String imageURL, String genreIds, String email) {
        return movieService.insertMovie(title, description, link, imageURL, genreIds, email);
    }

    public String deleteMovieById(Integer id, String email) {
        return movieService.deleteMovieById(id, email);
    }

    public Movie updateMovieById(Integer id, String title, String description, String link, String imageURL, String addedGenreIds, String removedGenreIds, String email) {
        return movieService.updateMovie(id, title, description, link, imageURL, addedGenreIds, removedGenreIds, email);
    }

    public String addGenreToMovie(Integer movieId, Integer genreId, String email) {
        return movieService.addGenreToMovie(movieId, genreId, email);
    }

    public String removeGenreFromMovie(Integer movieId, Integer genreId, String email) {
        return movieService.removeGenreFromMovie(movieId, genreId, email);
    }
}
