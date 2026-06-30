package io.github.ngraciano.libraryapi.controller;

import java.time.LocalDate;

public record AuthorDTO(String name, LocalDate dateBirth,String nationality) {
}
