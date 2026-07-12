package io.github.ngraciano.libraryapi.repository;

import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    Page<Book> findByAuthor(Author author, Pageable pageable);

    @Query(" select l from Book as l order by l.title,l.price")
    List<Book> findAllMethod();

    boolean existsByAuthor(Author author);

    Optional<Book> findByIsbn(String isbn);
}
