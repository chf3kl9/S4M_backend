package com.S4M.backend.services;

import com.S4M.backend.models.Genre;
import com.S4M.backend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepository repository;

    public Genre insertGenre(Genre genre) {
        return repository.save(genre);
    }

    public List<Genre> getAllGenres() {
        return repository.findAll();
    }

    public Optional<Genre> getGenreById(int id) {
        return repository.findById(id);
    }

    public void deleteGenreById(int id) {
        repository.deleteById(id);
    }

    public Genre updateGenre(Genre genre) {
        Genre existing = repository.findById(genre.getId()).get();
        copyNonNullProperties(genre, existing);
        return repository.save(existing);
    }

    void copyNonNullProperties(Genre updated, Genre original){
        original.setMovies(Optional.ofNullable(updated.getMovies()).orElse(original.getMovies()));
    }
}
