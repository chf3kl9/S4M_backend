package com.S4M.backend.services;

import com.S4M.backend.models.*;
import com.S4M.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminValidationService adminValidationService;

    public Movie insertMovie(String title, String description, String link, String imageURL, String genreIds, String email) {
        adminValidationService.isAuthorized(email);

        Movie movie = new Movie(title, description, link, imageURL);
        Movie m = movieRepository.save(movie);
        if (genreIds != null && genreIds.length() > 0) {
            for (String genreId : genreIds.split(",")) {
                addGenreToMovieInner(m.getId(), Integer.parseInt(genreId));
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

    public String deleteMovieById(int id, String email) {
        adminValidationService.isAuthorized(email);

        Movie m = movieRepository.findById(id).get();

        for(Genre g:m.getGenres()){
            removeGenreFromMovieInner(id, g.getId());
        }

        for(Comment c:m.getComments()){
            int removeIndex = 0;
            User u = c.getUser();
            for(Comment c1: u.getComments()){
                if (c1.getId() == c.getId()){
                    removeIndex = u.getComments().indexOf(c1);
                }
            }
            u.getComments().remove(removeIndex);
            userRepository.save(u);
        }

        for(Rating r:m.getRatings()){
            int removeIndex = 0;
            User u = r.getUser();
            for(Rating r1: u.getRatings()){
                if (r1.getId() == r.getId()){
                    removeIndex = u.getRatings().indexOf(r1);
                }
            }
            u.getRatings().remove(removeIndex);
            userRepository.save(u);
        }

        for(User u:userRepository.findAll()){
            int removeIndex = -1;
            for(Movie m1:u.getFavorites()){
                if (m1.getId() == m.getId())
                    removeIndex = u.getFavorites().indexOf(m1);
            }
            if (removeIndex > -1)
                u.getFavorites().remove(removeIndex);

            removeIndex = -1;
            for(Movie m1:u.getWatchedMovies()){
                if (m1.getId() == m.getId())
                    removeIndex = u.getWatchedMovies().indexOf(m1);
            }
            if (removeIndex > -1)
                u.getWatchedMovies().remove(removeIndex);
            userRepository.save(u);
        }

        movieRepository.deleteById(id);
        return "Movie is deleted";
    }

    public Movie updateMovie(int id, String title, String description, String link, String imageURL, String addedGenreIds, String removedGenreIds, String email) {
        adminValidationService.isAuthorized(email);
        Movie movie = movieRepository.findById(id).get();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setLink(link);
        movie.setImageURL(imageURL);
        Movie m = movieRepository.save(movie);
        if (addedGenreIds != null && addedGenreIds.length() > 0) {
            for (String genreId : addedGenreIds.split(",")) {
                addGenreToMovieInner(m.getId(), Integer.parseInt(genreId));
            }
        }
        if (removedGenreIds != null && removedGenreIds.length() > 0) {
            for (String genreId : removedGenreIds.split(",")) {
                removeGenreFromMovieInner(m.getId(), Integer.parseInt(genreId));
            }
        }
        return movieRepository.findById(m.getId()).get();
    }

    public String addGenreToMovie(int movieId, int genreId, String email) {
        adminValidationService.isAuthorized(email);
        return addGenreToMovieInner(movieId, genreId);
    }

    private String addGenreToMovieInner(int movieId, int genreId){
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

    public String removeGenreFromMovie(int movieId, int genreId, String email) {
        adminValidationService.isAuthorized(email);
        return removeGenreFromMovieInner(movieId, genreId);
    }

    public String removeGenreFromMovieInner(int movieId, int genreId){
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
