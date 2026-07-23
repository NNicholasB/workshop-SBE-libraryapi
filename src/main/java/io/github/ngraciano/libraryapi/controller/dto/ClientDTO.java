package io.github.ngraciano.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClientDTO(
        UUID id,
        @NotBlank(message = "Obligatory Field.")
        String clientId,
        @NotNull(message = "Obligatory Field.")
        String clientSecret,
        @NotBlank(message = "Obligatory Field.")
        String redirectUri,
        @NotBlank(message="Obligatory Field")
        String scope

) {
}
