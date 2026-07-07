package io.github.ngraciano.libraryapi.controller.mappers;


import io.github.ngraciano.libraryapi.controller.dto.AuthorDTO;
import io.github.ngraciano.libraryapi.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO dto);

    AuthorDTO toDTO(Author author);
}
