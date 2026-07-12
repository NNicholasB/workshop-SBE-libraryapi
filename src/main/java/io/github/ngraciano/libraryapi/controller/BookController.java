package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.ErrorResponse;
import io.github.ngraciano.libraryapi.controller.dto.RegisterBookDTO;
import io.github.ngraciano.libraryapi.controller.dto.ResultSearchBookDTO;
import io.github.ngraciano.libraryapi.controller.mappers.BookMapper;
import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.model.GenderBook;
import io.github.ngraciano.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<Page<ResultSearchBookDTO>>search(@RequestParam(value="isbn",required = false)String isbn,
                                                           @RequestParam(value="title",required = false)String title,
                                                           @RequestParam(value="nameAuthor",required = false)String nameAuthor,
                                                           @RequestParam(value="gender",required = false)GenderBook gender,
                                                           @RequestParam(value="yearPublication",required = false)Integer yearPublication,
                                                           @RequestParam(value="page",defaultValue="0") Integer page,
                                                           @RequestParam(value="sizePage",defaultValue = "10") Integer sizePage
                                                           ){
        Page<Book> pageResult=service.search(isbn, title, nameAuthor, gender, yearPublication,page,sizePage);
        Page<ResultSearchBookDTO> result = pageResult.map(mapper::toDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id,@RequestBody @Valid RegisterBookDTO dto){
       return service.findById(UUID.fromString(id)).map(book ->{
           Book entity = mapper.toEntity(dto);
           book.setDatePublication(entity.getDatePublication());
           book.setIsbn(entity.getIsbn());
           book.setPrice(entity.getPrice());
           book.setGender(entity.getGender());
           book.setTitle(entity.getTitle());
           book.setAuthor(entity.getAuthor());
           service.update(book);
           return ResponseEntity.noContent().build();
       }).orElseGet(()->ResponseEntity.notFound().build());


    }
}
