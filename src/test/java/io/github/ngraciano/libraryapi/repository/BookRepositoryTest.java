package io.github.ngraciano.libraryapi.repository;


import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.model.GenderBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository repo;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveTest(){
        Book book=new Book();
        book.setIsbn("12312");
        book.setPrice(BigDecimal.valueOf(140));
        book.setGender(GenderBook.Fiction);
        book.setTitle("Star Wars");
        book.setDataPublication(LocalDate.of(1977,2,13));

        Author author=authorRepository.findById(UUID.fromString("ad07932c-2414-41c7-bfa1-148ddc68d5f8")).orElse(null);

        book.setAuthor(author);
        repo.save(book);

    }



}