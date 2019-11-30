package com.S4M.backend.controllers;

import com.S4M.backend.models.Genre;
import com.S4M.backend.models.Movie;
import com.S4M.backend.services.GenreService;
import com.S4M.backend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("genres")
@RestController
@CrossOrigin(origins = {"https://s4m-frontend.herokuapp.com", "http://s4m-frontend.herokuapp.com", "s4m-frontend.herokuapp.com"})
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public boolean insertGenre(@RequestBody Genre genre) {
        if (validateGenre(genre))
            return genreService.insertGenre(genre) != null;
        return false;
    }

    @GetMapping
    public Object getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping(path = "{id}")
    public Genre getGenreById(@PathVariable("id") int id) {
        return genreService.getGenreById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteGenreById(@PathVariable("id") int id) {
        genreService.deleteGenreById(id);
    }

    @PutMapping
    public boolean updateGenreById(@RequestBody Genre genre) {
        if (validateGenre(genre))
            return genreService.updateGenre(genre) != null;
        return false;
    }

    private boolean validateGenre(Genre genre) {
        return genre.getName() != null;
    }
}
