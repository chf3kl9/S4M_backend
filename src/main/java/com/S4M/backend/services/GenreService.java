package com.S4M.backend.services;

import com.S4M.backend.models.Genre;
import com.S4M.backend.models.Movie;
import com.S4M.backend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;
    @Autowired
    MovieService movieService;

    @Autowired
    AdminValidationService adminValidationService;

    public Genre insertGenre(String name, String email) {
        adminValidationService.isAuthorized(email);
        Genre genre = new Genre(name);
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(int id) {
        return genreRepository.findById(id);
    }

    public String deleteGenreById(int id, String email) {
        adminValidationService.isAuthorized(email);

        Genre g = genreRepository.findById(id).get();

        for(Movie m:g.getMovies()){
            movieService.removeGenreFromMovieInner(m.getId(), id);
        }

        genreRepository.deleteById(id);
        return "Genre is deleted";
    }

    public Genre updateGenre(int id, String name, String email) {
        adminValidationService.isAuthorized(email);
        Genre genre = genreRepository.findById(id).get();
        genre.setName(name);
        return genreRepository.save(genre);
    }
}
