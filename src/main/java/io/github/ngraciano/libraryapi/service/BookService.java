package io.github.ngraciano.libraryapi.service;


import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.model.GenderBook;
import io.github.ngraciano.libraryapi.repository.BookRepository;
import io.github.ngraciano.libraryapi.repository.specs.BookSpecs;
import io.github.ngraciano.libraryapi.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.ngraciano.libraryapi.repository.specs.BookSpecs.*;



@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookValidator validator;

    public Book save(Book book) {
        validator.validator(book);
        return repository.save(book);
    }

    public Optional<Book> findById(UUID id){
        return repository.findById(id);
    }

    public void delete(Book book){
        repository.delete(book);
    }

    public Page<Book> search(String isbn, String title, String nameAuthor, GenderBook gender, Integer yearPublication,Integer page,Integer sizePage){
//        Specification<Book> specs= Specification.where(BookSpecs.isbnEqual(isbn).and(BookSpecs.genderEqual(gender).and(BookSpecs.titleLike(title))));

        Specification<Book> specs=Specification.where((root, query, cb) ->cb.conjunction());
       if (isbn!=null){
           specs=specs.and(isbnEqual(isbn));
       }
       if (title != null){
           specs=specs.and(titleLike(title));
       }
       if (gender !=null){
           specs=specs.and(genderEqual(gender));
       }
       if (yearPublication != null){
           specs=specs.and(yearPublicationEqual(yearPublication));
       }
        if(nameAuthor != null){
            specs=specs.and(nameAuthorLike(nameAuthor));
        }
        Pageable pageRequest= PageRequest.of(page,sizePage);
        return  repository.findAll(specs,pageRequest);
    }


    public void update(Book book) {
    if (book.getId() ==null){
        throw new IllegalArgumentException("for update,is necessary book saved in the db");
    }
    validator.validator(book);
    repository.save(book);
}


}
