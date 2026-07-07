package io.github.ngraciano.libraryapi.service;


import io.github.ngraciano.libraryapi.exceptions.OperationNotPermitted;
import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.repository.AuthorRepository;
import io.github.ngraciano.libraryapi.repository.BookRepository;
import io.github.ngraciano.libraryapi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
        private final AuthorValidator validator;
        private final AuthorRepository repository;
        private final BookRepository bookRepository;

    public Author save(Author author)
    {
        validator.Validate(author);
        return repository.save(author);
    }

    public void update(Author author){
        if (author.getId()==null){
            throw new IllegalArgumentException("for update,is necessary author saved in the db");
        }
        validator.Validate(author);
         repository.save(author);
    }


    public Optional<Author> findById(UUID id){
        return repository.findById(id);
    }

    public void delete(Author author){
        if (hasBook(author)){
            throw new OperationNotPermitted("Author have book registred");
        }
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

    public List<Author> searchByExample(String name,String nationality){
        Author author=new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher matcher=ExampleMatcher.matching().withIgnorePaths("id","dateBirth").withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Author> authorExample=Example.of(author,matcher);
        return repository.findAll(authorExample);
    }

    public boolean hasBook(Author author){
        return bookRepository.existsByAuthor(author);
    }
}
