package com.S4M.backend.services;

import com.S4M.backend.models.Comment;
import com.S4M.backend.models.Movie;
import com.S4M.backend.models.User;
import com.S4M.backend.repositories.CommentRepository;
import com.S4M.backend.repositories.MovieRepository;
import com.S4M.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CommentRepository commentRepository;

    public String deleteCommentById(int id, String email) {
        Optional<Comment> c1 = commentRepository.findById(id);
        if (!c1.isPresent()){
            return "The comment does not exist";
        }
        Comment c = c1.get();
        if (AdminValidationService.isAdmin(email) || c.getUser().getEmail().equals(email)){
            commentRepository.deleteById(id);
            return "Comment successfully deleted";
        } else {
            return "You are not authorized to delete this comment";
        }
    }

    public String placeComment(String email, int movieId, String text){
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

        Comment comment = new Comment(text, user, movie);

        commentRepository.save(comment);
        userRepository.save(user);
        movieRepository.save(movie);


        return "The comment was successfully placed";
    }
}
