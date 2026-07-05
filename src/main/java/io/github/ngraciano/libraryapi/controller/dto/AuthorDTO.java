package io.github.ngraciano.libraryapi.controller.dto;

import io.github.ngraciano.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "Obligatorily Field")
        @Size(min=2, max=100,message="field outside standard size")
        String name,
        @NotNull(message = "Obligatorily Field")
       @Past(message ="can't be a future date")
        LocalDate dateBirth,
        @NotBlank(message = "Obligatorily Field")
        @Size(min=2,max=50,message = "field outside standard size")
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
