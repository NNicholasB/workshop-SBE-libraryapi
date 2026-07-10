package io.github.ngraciano.libraryapi.validator;


import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.exceptions.FieldInvalidExcepiton;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private static final int year_price=2020;


    private final BookRepository repository;

    public void validator(Book book){
        if (existBookWithIsbn(book)){
            throw new DuplicateEntryException("Book already registered with an ISBN");
        }
        if (isPriceOBrigatoryNull(book)) {
            throw new FieldInvalidExcepiton("price","Books published from 2020 onwards, the price must be stated");
        }
    }

    private boolean isPriceOBrigatoryNull(Book book) {
        return book.getPrice()==null && book.getDatePublication().getYear()>=year_price;
    }

    private boolean existBookWithIsbn(Book book){
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());

        if (book.getId()==null){
            return bookFound.isPresent();
        }
        return bookFound.map(Book::getId).stream().anyMatch(id->!id.equals(book.getId()));
    }


}
