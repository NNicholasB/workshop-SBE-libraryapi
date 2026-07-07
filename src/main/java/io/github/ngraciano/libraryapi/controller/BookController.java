package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.ErrorResponse;
import io.github.ngraciano.libraryapi.controller.dto.RegisterBookDTO;
import io.github.ngraciano.libraryapi.controller.mappers.BookMapper;
import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final BookMapper mapper;
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody  @Valid RegisterBookDTO dto){
        try{
            Book book=mapper.toEntity(dto);
            service.save(book);

            return ResponseEntity.ok(book);
        }catch (DuplicateEntryException e){
            var errorDTO= ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }
}
