package io.github.ngraciano.libraryapi.controller.dto;


import io.github.ngraciano.libraryapi.model.GenderBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultSearchBookDTO(
        UUID id,
        String isbn,
        String title,
        LocalDate datePublish,
        GenderBook gender,
        BigDecimal price,
        AuthorDTO author

) {
}
