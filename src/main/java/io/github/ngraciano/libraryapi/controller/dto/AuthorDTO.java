package io.github.ngraciano.libraryapi.controller.dto;

import io.github.ngraciano.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "Obligatorily Field")
        String name,
        @NotNull(message = "Obligatorily Field")
        LocalDate dateBirth,
        @NotBlank(message = "Obligatorily Field")
        String nationality
) {


    public Author mapToAuthor(){
        Author author=new Author();
        author.setName(this.name);
        author.setDateBirth(this.dateBirth);
        author.setNationality(this.nationality);
        return author;
    }
}
