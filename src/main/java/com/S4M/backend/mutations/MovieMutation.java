package com.S4M.backend.mutations;

import com.S4M.backend.models.Movie;
import com.S4M.backend.services.MovieService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class MovieMutation implements GraphQLMutationResolver {

    private final MovieService movieService;

    public MovieMutation(MovieService movieService) {
        this.movieService = movieService;
    }

    public Movie createMovie(String title, String description, String link, String imageURL) {
        return movieService.insertMovie(title, description, link, imageURL);
    }

    public String deleteMovieById(Integer id) {
        return movieService.deleteMovieById(id);
    }

    public Movie updateMovieById(Integer id, String title, String description, String link, String imageURL) {
        return movieService.updateMovie(id, title, description, link, imageURL);
    }

    public String addGenreToMovie(Integer movieId, Integer genreId){
        return movieService.addGenreToMovie(movieId, genreId);
    }

    public String removeGenreFromMovie(Integer movieId, Integer genreId){
        return movieService.removeGenreFromMovie(movieId, genreId);
    }
}
