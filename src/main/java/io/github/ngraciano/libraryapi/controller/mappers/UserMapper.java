package io.github.ngraciano.libraryapi.controller.mappers;


import io.github.ngraciano.libraryapi.controller.dto.UserDTO;
import io.github.ngraciano.libraryapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {

    User toEntity(UserDTO dto);
}
