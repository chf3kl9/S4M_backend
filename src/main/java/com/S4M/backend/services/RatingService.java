package com.S4M.backend.services;

import com.S4M.backend.models.Movie;
import com.S4M.backend.models.Rating;
import com.S4M.backend.models.User;
import com.S4M.backend.repositories.RatingRepository;
import com.S4M.backend.repositories.MovieRepository;
import com.S4M.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingRepository ratingRepository;

    public String rateMovie(String email, int movieId, int rate){
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
        Rating rating = null;

        for(Rating r: movie.getRatings()){
            if (r.getUser().getId() == user.getId())
                rating = r;
        }

        if (rating == null){
            rating = new Rating(rate, user, movie);
            rating = ratingRepository.save(rating);

            user.getRatings().add(rating);
            movie.getRatings().add(rating);
            userRepository.save(user);
            movieRepository.save(movie);
        } else {
            rating.setValue(rate);
            ratingRepository.save(rating);
        }


        return "The Rating was successfully placed";
    }
}
