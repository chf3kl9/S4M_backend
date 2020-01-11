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

    public Genre insertGenre(String name) {
        Genre genre = new Genre(name);
        return repository.save(genre);
    }

    public List<Genre> getAllGenres() {
        return repository.findAll();
    }

    public Optional<Genre> getGenreById(int id) {
        return repository.findById(id);
    }

    public String deleteGenreById(int id) {
        repository.deleteById(id);
        return "Genre is deleted";
    }

    public Genre updateGenre(int id, String name) {
        Genre genre = repository.findById(id).get();
        genre.setName(name);
        return repository.save(genre);
    }
}
