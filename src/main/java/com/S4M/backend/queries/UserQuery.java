package com.S4M.backend.queries;

import com.S4M.backend.models.User;
import com.S4M.backend.services.UserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class UserQuery implements GraphQLQueryResolver {

    private final UserService userService;

    public UserQuery(UserService userService) {
        this.userService = userService;
    }

    public List<User> users() {
        return userService.getAllUsers();
    }

    public Optional<User> user(@Nullable Integer id, @Nullable String name){
        return userService.getUser(id, name);
    }
}
