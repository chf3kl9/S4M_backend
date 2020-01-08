package com.S4M.backend.services;

import com.S4M.backend.models.User;
import com.S4M.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User createUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return repository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public void deleteUserById(int id) {
        repository.deleteById(id);
    }

    public User updateUser(User user) {
        User existing = repository.findById(user.getId()).get();
        copyNonNullProperties(user, existing);
        return repository.save(user);
    }

    void copyNonNullProperties(User updated, User original){
        updated.setComments(Optional.ofNullable(updated.getComments()).orElse(original.getComments()));
        updated.setFavorites(Optional.ofNullable(updated.getFavorites()).orElse(original.getFavorites()));
        updated.setRatings(Optional.ofNullable(updated.getRatings()).orElse(original.getRatings()));
        updated.setWatchedMovies(Optional.ofNullable(updated.getWatchedMovies()).orElse(original.getWatchedMovies()));
    }
}
