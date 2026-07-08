package io.github.ngraciano.libraryapi.controller.mappers;

import io.github.ngraciano.libraryapi.controller.dto.RegisterBookDTO;
import io.github.ngraciano.libraryapi.controller.dto.ResultSearchBookDTO;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",uses = AuthorMapper.class)
public abstract class BookMapper {


    @Autowired
     AuthorRepository authorRepository;

    @Mapping(target="author", expression = "java(authorRepository.findById(dto.idAuthor()).orElse(null) )")
    public abstract Book toEntity(RegisterBookDTO dto);

    public abstract ResultSearchBookDTO toDTO(Book book);
}
