package com.S4M.backend.mutations;

import com.S4M.backend.models.Genre;
import com.S4M.backend.services.GenreService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class GenreMutation implements GraphQLMutationResolver {

    private final GenreService genreService;

    public GenreMutation(GenreService genreService) {
        this.genreService = genreService;
    }


    public Genre createGenre(String name) {
        return genreService.insertGenre(name);
    }

    public String deleteGenreById(Integer id) {
        return genreService.deleteGenreById(id);
    }

    public Genre updateGenreById(Integer id, String name) {
        return genreService.updateGenre(id, name);
    }
}
