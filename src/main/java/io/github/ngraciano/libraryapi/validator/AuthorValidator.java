package io.github.ngraciano.libraryapi.validator;


import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    public void Validate(Author author){
    if (existAuthor(author)){
        throw new DuplicateEntryException("Author already exists ");
    }
    }

    private boolean existAuthor(Author author){
        Optional<Author> authorExist=repository.findByNameAndDateBirthAndNationality(author.getName(),author.getDateBirth(),author.getNationality());
        if (author.getId()==null){
            return authorExist.isPresent();
        }
        return !author.getId().equals(authorExist.get().getId()) && authorExist.isPresent();
    }
}
