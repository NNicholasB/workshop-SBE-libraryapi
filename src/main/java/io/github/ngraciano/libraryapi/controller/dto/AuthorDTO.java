package io.github.ngraciano.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "Obligatory Field.")
        @Size(min=2, max=100,message="field outside standard size.")
        String name,
        @NotNull(message = "Obligatory Field.")
       @Past(message ="The date cannot be in the future.")
        LocalDate dateBirth,
        @NotBlank(message = "Obligatory Field.")
        @Size(min=2,max=50,message = "field outside standard size.")
        String nationality
) {

}
