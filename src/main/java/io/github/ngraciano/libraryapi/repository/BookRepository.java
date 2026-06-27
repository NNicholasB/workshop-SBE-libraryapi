package io.github.ngraciano.libraryapi.repository;

import io.github.ngraciano.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
