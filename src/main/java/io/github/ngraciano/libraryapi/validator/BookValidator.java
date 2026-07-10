package io.github.ngraciano.libraryapi.validator;


import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {


    private final BookRepository repository;

    public void validator(Book book){
        if (existBookWithIsbn(book)){
            throw new DuplicateEntryException("Book already registered with an ISBN");
        }
    }
    private boolean existBookWithIsbn(Book book){
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());

        if (book.getId()==null){
            return bookFound.isPresent();
        }
        return bookFound.map(Book::getId).stream().anyMatch(id->!id.equals(book.getId()));
    }

}
