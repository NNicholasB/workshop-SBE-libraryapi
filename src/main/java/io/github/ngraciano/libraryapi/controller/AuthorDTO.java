package io.github.ngraciano.libraryapi.controller;

import io.github.ngraciano.libraryapi.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(UUID id, String name, LocalDate dateBirth, String nationality) {


    public Author mapToAuthor(){
        Author author=new Author();
        author.setName(this.name);
        author.setDateBirth(this.dateBirth);
        author.setNationality(this.nationality);
        return author;
    }
}
