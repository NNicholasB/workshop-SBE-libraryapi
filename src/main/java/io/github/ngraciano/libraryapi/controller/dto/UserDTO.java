package io.github.ngraciano.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDTO(
                        @NotBlank(message = "Obligatory field.")
                       String login,
                        @NotBlank(message = "Obligatory field.")
                        String password,
                       @Email(message = "Invalid email")
                        @NotBlank(message = "Obligatory field.")
                        String email,
                        List<String> roles) {

}
