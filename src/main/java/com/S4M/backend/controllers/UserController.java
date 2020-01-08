package com.S4M.backend.controllers;

import com.S4M.backend.models.Genre;
import com.S4M.backend.models.User;
import com.S4M.backend.services.GenreService;
import com.S4M.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public boolean insertGenre(@RequestBody User user) {
        if (validateUser(user))
            return userService.createUser(user) != null;
        return false;
    }

    @GetMapping
    public Object getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "email/{email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email)
                .orElse(null);
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

    @PutMapping
    public boolean updateUserById(@RequestBody User user) {
        if (validateUser(user))
            return userService.updateUser(user) != null;
        return false;
    }

    private boolean validateUser(User user) {
        return user.getEmail() != null;
    }
}
