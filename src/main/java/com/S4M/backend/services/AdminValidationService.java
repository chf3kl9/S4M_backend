package com.S4M.backend.services;

import com.S4M.backend.models.User;
import com.S4M.backend.repositories.UserRepository;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminValidationService {

    @Autowired
    UserRepository userRepository;

    public void isAuthorized(String email){ // when it requires admin rights
        if (!isAdmin(email))
            throw new GraphQLException("You do not have sufficient rights to do this.");
    }

    public boolean isAdmin(String email){
        Optional<User> u = userRepository.findByEmail(email);
        return u.map(User::isAdmin).orElse(false);
    }
}
