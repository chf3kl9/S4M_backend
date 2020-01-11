package com.S4M.backend.services;

import com.S4M.backend.models.Movie;
import com.S4M.backend.models.User;
import com.S4M.backend.repositories.MovieRepository;
import com.S4M.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    public User createUser(String email, boolean isAdmin) {
        User user = new User(email, isAdmin);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Integer id, String email){
        if (id != null)
        {
            return userRepository.findById(id);
        }
        if (email != null)
        {
            return userRepository.findByEmail(email);
        }
        return Optional.empty();
    }

    public String deleteUserById(int id) {
        userRepository.deleteById(id);
        return "User successfully deleted";
    }

    public User updateUser(int id, String email, boolean isAdmin) {
        User user = userRepository.findById(id).get();
        user.setEmail(email);
        user.setAdmin(isAdmin);
        return userRepository.save(user);
    }

    public String addMovieToFavorites(String email, int movieId) {
        Optional<User> user1 = userRepository.findByEmail(email);
        Optional<Movie> movie1 = movieRepository.findById(movieId);

        if (!movie1.isPresent()) {
            return "The movie does not exist";
        }
        if (!user1.isPresent()) {
            return "The user does not exist";
        }

        Movie movie = movie1.get();
        User user = user1.get();

        if (user.getFavorites().contains(movie)) {
            return "The movie is already added to favorites";
        }
        user.getFavorites().add(movie);
        userRepository.save(user);
        return "The movie was successfully added to favorites";
    }

    public String addMovieToWatchlist(String email, int movieId) {
        Optional<User> user1 = userRepository.findByEmail(email);
        Optional<Movie> movie1 = movieRepository.findById(movieId);

        if (!movie1.isPresent()) {
            return "The movie does not exist";
        }
        if (!user1.isPresent()) {
            return "The user does not exist";
        }

        Movie movie = movie1.get();
        User user = user1.get();

        if (user.getWatchedMovies().contains(movie)) {
            return "The movie is already added to watchlist";
        }
        user.getWatchedMovies().add(movie);
        userRepository.save(user);
        return "The movie was successfully added to watchlist";
    }

    public String removeMovieFromFavorites(String email, int movieId) {
        Optional<User> user1 = userRepository.findByEmail(email);
        Optional<Movie> movie1 = movieRepository.findById(movieId);

        if (!movie1.isPresent()) {
            return "The movie does not exist";
        }
        if (!user1.isPresent()) {
            return "The user does not exist";
        }

        Movie movie = movie1.get();
        User user = user1.get();

        if (!user.getFavorites().contains(movie)) {
            return "The movie is already removed from favorites";
        }
        user.getFavorites().remove(movie);
        userRepository.save(user);
        return "The movie was successfully removed from favorites";
    }

    public String removeMovieFromWatchlist(String email, int movieId) {
        Optional<User> user1 = userRepository.findByEmail(email);
        Optional<Movie> movie1 = movieRepository.findById(movieId);

        if (!movie1.isPresent()) {
            return "The movie does not exist";
        }
        if (!user1.isPresent()) {
            return "The user does not exist";
        }

        Movie movie = movie1.get();
        User user = user1.get();

        if (!user.getWatchedMovies().contains(movie)) {
            return "The movie is not part of watchlist";
        }
        user.getWatchedMovies().remove(movie);
        userRepository.save(user);
        return "The movie was successfully removed from watchlist";
    }
}
