package io.github.ngraciano.libraryapi.service;


import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

        private AuthorRepository repository;

    public AuthorService(AuthorRepository repository){
        this.repository=repository;
    }

    public Author save(Author author){
        return repository.save(author);
    }

    public void update(Author author){
        if (author.getId()==null){
            throw new IllegalArgumentException("for update,is necessary author saved in the db");
        }
         repository.save(author);
    }


    public Optional<Author> findById(UUID id){
        return repository.findById(id);
    }

    public void delete(Author author){
         repository.delete(author);
    }

    public List<Author> search(String name,String nationality){
    if (name !=null  && nationality != null){
        return repository.findByNameAndNationality(name,nationality);
    }
    if(name !=null){
        return repository.findByName(name);
    }
    if (nationality != null){
        return repository.findByNationality(nationality);
    }
    return repository.findAll();
    }
}
