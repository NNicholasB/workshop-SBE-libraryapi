package io.github.ngraciano.libraryapi.controller.dto;

import io.github.ngraciano.libraryapi.model.GenderBook;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterBookDTO(
        @ISBN
        @NotBlank(message = "Obligatory field.")
        String isbn,
        @NotBlank(message = "Obligatory field.")
        String title,
        @NotNull(message = "Obligatory field.")
        @Past(message = "The date cannot be in the future.")
        LocalDate datePublication,

        GenderBook gender,

        BigDecimal price,
        @NotNull(message = "Obligatory field")
        UUID idAuthor
) {


}
