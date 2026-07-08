package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.AuthorDTO;
import io.github.ngraciano.libraryapi.controller.dto.ErrorResponse;
import io.github.ngraciano.libraryapi.controller.mappers.AuthorMapper;
import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.exceptions.OperationNotPermitted;
import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO dto) {

            Author author = mapper.toEntity(dto);

            service.save(author);

            URI location = generateHeaderLocation(author.getId());
            return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> detailsByID(@PathVariable String id) {
        UUID idAuthor = UUID.fromString(id);

        return service.findById(idAuthor).map(author -> {
            AuthorDTO dto = mapper.toDTO(author);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
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
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nationality", required = false) String nationality) {
        List<Author> result = service.searchByExample(name, nationality);
        List<AuthorDTO> list = result.stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
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
