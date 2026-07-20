package io.github.ngraciano.libraryapi.controller.mappers;

import io.github.ngraciano.libraryapi.controller.dto.ClientDTO;
import io.github.ngraciano.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO dto);
}
