package io.github.ngraciano.libraryapi.repository;

import io.github.ngraciano.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {


    @Query(" select l from Book as l order by l.title,l.price")
    List<Book> findAllMethod();
}
