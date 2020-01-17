package com.S4M.backend.mutations;

import com.S4M.backend.models.User;
import com.S4M.backend.services.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;

    public UserMutation(UserService userService) {
        this.userService = userService;
    }

    public User createUser(String email, Boolean isAdmin) {
        return userService.createUser(email, isAdmin != null && isAdmin);
    }

    public String deleteUserById(Integer id, String email) {
        return userService.deleteUserById(id, email);
    }

    public User updateUserById(Integer id, String email, Boolean isAdmin) {
        return userService.updateUser(id, email, isAdmin != null && isAdmin);
    }

    public String addMovieToFavorites(String email, Integer movieId){
        return userService.addMovieToFavorites(email, movieId);
    }

    public String addMovieToWatchlist(String email, Integer movieId){
        return userService.addMovieToWatchlist(email, movieId);
    }

    public String removeMovieFromFavorites(String email, Integer movieId){
        return userService.removeMovieFromFavorites(email, movieId);
    }

    public String removeMovieFromWatchlist(String email, Integer movieId){
        return userService.removeMovieFromWatchlist(email, movieId);
    }
}
