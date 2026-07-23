package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.AuthorDTO;
import io.github.ngraciano.libraryapi.controller.dto.ErrorResponse;
import io.github.ngraciano.libraryapi.controller.mappers.AuthorMapper;
import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.exceptions.OperationNotPermitted;
import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.model.User;
import io.github.ngraciano.libraryapi.security.SecurityService;
import io.github.ngraciano.libraryapi.service.AuthorService;
import io.github.ngraciano.libraryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
@Tag(name="Authors")
public class AuthorController implements GenericController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar", description = "cadastrar novo autor" )
    @ApiResponses({
            @ApiResponse(responseCode="201",description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode="422",description = "Erro de validacao"),
            @ApiResponse(responseCode="409",description = "Autor ja cadastrado")
    })
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO dto) {
        Author author = mapper.toEntity(dto);
            service.save(author);
            URI location = generateHeaderLocation(author.getId());
            return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    @Operation(summary = "Obter detalhes", description = "Retornar os dados do autor pelo Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "400", description = "Autor nao encontrado")
    })
    public ResponseEntity<AuthorDTO> detailsByID(@PathVariable String id) {
        UUID idAuthor = UUID.fromString(id);

        return service.findById(idAuthor).map(author -> {
            AuthorDTO dto = mapper.toDTO(author);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Deletar", description = "Deleta um autor existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor nao encontrado"),
            @ApiResponse(responseCode = "400", description = "Autor possui um livro cadastrado")
    })
    public ResponseEntity<Object> deleteById(@PathVariable String id) {
        UUID idAuthor = UUID.fromString(id);
        Optional<Author> author = service.findById(idAuthor);

            if (author.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        service.delete(author.get());
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR','GERENTE')")
    @Operation(summary = "Pesquisar", description = "Realiza uma pesquisar por parametros")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso"),
    })
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nationality", required = false) String nationality) {
        List<Author> result = service.searchByExample(name, nationality);
        List<AuthorDTO> list = result.stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Atualizar", description = "Atualiza um autor existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor nao encontrado"),
            @ApiResponse(responseCode = "409", description = "Autor ja cadastrado")
    })
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody @Valid AuthorDTO dto) {
        UUID idAuthor = UUID.fromString(id);
        Optional<Author> authorOp = service.findById(idAuthor);

        if (authorOp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Author author = authorOp.get();
        author.setName(dto.name());
        author.setNationality(dto.nationality());
        author.setDateBirth(dto.dateBirth());
        service.update(author);

        return ResponseEntity.noContent().build();
    }
}
