package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.ErrorResponse;
import io.github.ngraciano.libraryapi.controller.dto.RegisterBookDTO;
import io.github.ngraciano.libraryapi.controller.dto.ResultSearchBookDTO;
import io.github.ngraciano.libraryapi.controller.mappers.BookMapper;
import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid RegisterBookDTO dto) {

        Book book = mapper.toEntity(dto);
        service.save(book);
        URI uri = generateHeaderLocation(book.getId());
        return ResponseEntity.created(uri).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<ResultSearchBookDTO> getDetails(@PathVariable("id") String id){
        return  service.findById(UUID.fromString(id)).map(book->{
            ResultSearchBookDTO dto=mapper.toDTO(book);
            return ResponseEntity.ok(dto);
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        return service.findById(UUID.fromString(id)).map(book->{
            service.delete(book);
            return ResponseEntity.noContent().build();
        }).orElseGet(()->ResponseEntity.notFound().build());
    }
}
