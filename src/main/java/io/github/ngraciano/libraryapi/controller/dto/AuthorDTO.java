package io.github.ngraciano.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name ="Author")
public record AuthorDTO(
        UUID id,
        @NotBlank(message = "Obligatory Field.")
        @Size(min=2, max=100,message="field outside standard size.")
        @Schema(name = "nome")
        String name,
        @NotNull(message = "Obligatory Field.")
       @Past(message ="The date cannot be in the future.")
        @Schema(name = "data de nascimento")
        LocalDate dateBirth,
        @NotBlank(message = "Obligatory Field.")
        @Size(min=2,max=50,message = "field outside standard size.")
        @Schema(name = "nacionalidade")
        String nationality
) {

}
