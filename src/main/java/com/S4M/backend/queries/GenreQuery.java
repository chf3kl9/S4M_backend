package com.S4M.backend.queries;

import com.S4M.backend.models.Genre;
import com.S4M.backend.services.GenreService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com", "http://localhost:3000"})
public class GenreQuery implements GraphQLQueryResolver {

    private final GenreService genreService;

    public GenreQuery(GenreService genreService) {
        this.genreService = genreService;
    }

    public List<Genre> genres() {
        return genreService.getAllGenres();
    }

    public Optional<Genre> genre(Integer id) {
        return genreService.getGenreById(id);
    }
}
