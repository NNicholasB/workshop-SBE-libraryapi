package io.github.ngraciano.libraryapi.service;


import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book save(Book book) {
        return repository.save(book);
    }
}
