package io.github.ngraciano.libraryapi.service;


import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

        private AuthorRepository repository;

    public AuthorService(AuthorRepository repository){
        this.repository=repository;
    }

    public Author save(Author author){
        return repository.save(author);
    }
}
