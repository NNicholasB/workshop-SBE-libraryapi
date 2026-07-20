package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.ClientDTO;
import io.github.ngraciano.libraryapi.controller.mappers.ClientMapper;
import io.github.ngraciano.libraryapi.model.Client;
import io.github.ngraciano.libraryapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ClientDTO dto ){
        Client client=mapper.toEntity(dto);
        service.save(client);
    }
}
