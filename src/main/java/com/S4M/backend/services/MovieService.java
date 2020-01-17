package com.S4M.backend.services;

import com.S4M.backend.models.Genre;
import com.S4M.backend.models.Movie;
import com.S4M.backend.repositories.GenreRepository;
import com.S4M.backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;

    public Movie insertMovie(String title, String description, String link, String imageURL, String genreIds) {
        Movie movie = new Movie(title, description, link, imageURL);
        Movie m = movieRepository.save(movie);
        if (genreIds != null && genreIds.length() > 0) {
            for (String genreId : genreIds.split(",")) {
                addGenreToMovie(m.getId(), Integer.parseInt(genreId));
            }
        }
        return movieRepository.findById(m.getId()).get();
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    public String deleteMovieById(int id) {
        movieRepository.deleteById(id);
        return "Movie is deleted";
    }

    public Movie updateMovie(int id, String title, String description, String link, String imageURL, String addedGenreIds, String removedGenreIds) {
        Movie movie = movieRepository.findById(id).get();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setLink(link);
        movie.setImageURL(imageURL);
        Movie m = movieRepository.save(movie);
        if (addedGenreIds != null && addedGenreIds.length() > 0) {
            for (String genreId : addedGenreIds.split(",")) {
                addGenreToMovie(m.getId(), Integer.parseInt(genreId));
            }
        }
        if (removedGenreIds != null && removedGenreIds.length() > 0) {
            for (String genreId : removedGenreIds.split(",")) {
                removeGenreFromMovie(m.getId(), Integer.parseInt(genreId));
            }
        }
        return movieRepository.findById(m.getId()).get();
    }

    public String addGenreToMovie(int movieId, int genreId){
        Optional<Movie> movie1 = movieRepository.findById(movieId);
        Optional<Genre> genre1 = genreRepository.findById(genreId);

        if (!movie1.isPresent()){
            return "The movie does not exist";
        }
        if (!genre1.isPresent()){
            return "The genre does not exist";
        }

        Movie movie = movie1.get();
        Genre genre = genre1.get();

        for(Genre g:movie.getGenres()){
            if (g.getId() == genre.getId())
                return "The genre is already added to the movie";
        }

        movie.getGenres().add(genre);
        movieRepository.save(movie);
        return "The genre was successfully added to the movie";
    }

    public String removeGenreFromMovie(int movieId, int genreId){
        Optional<Movie> movie1 = movieRepository.findById(movieId);
        Optional<Genre> genre1 = genreRepository.findById(genreId);

        if (!movie1.isPresent()){
            return "The movie does not exist";
        }
        if (!genre1.isPresent()){
            return "The genre does not exist";
        }

        Movie movie = movie1.get();
        Genre genre = genre1.get();

        int removeIndex = 0;

        boolean found = false;
        for(Genre g: movie.getGenres()){
            if (g.getId() == genre.getId()){
                found = true;
                removeIndex = movie.getGenres().indexOf(g);
                System.out.println(removeIndex);
            }
        }

        if (!found){
            return "The genre is not part of this movie";
        }
        movie.getGenres().remove(removeIndex);
        movieRepository.save(movie);
        return "The genre was successfully removed from the movie";
    }
}
