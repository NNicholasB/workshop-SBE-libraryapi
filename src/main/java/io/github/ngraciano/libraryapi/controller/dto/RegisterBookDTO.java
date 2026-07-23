package io.github.ngraciano.libraryapi.controller.dto;

import io.github.ngraciano.libraryapi.model.GenderBook;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(name = "Titulo")
        String title,
        @NotNull(message = "Obligatory field.")
        @Past(message = "The date cannot be in the future.")
        @Schema(name = "Data de Publicacao")
        LocalDate datePublication,

        GenderBook gender,

        BigDecimal price,
        @NotNull(message = "Obligatory field")
        UUID idAuthor
) {


}
