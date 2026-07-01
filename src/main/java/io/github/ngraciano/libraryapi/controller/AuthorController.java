package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.service.AuthorService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("authors")
public class AuthorController {


    private final AuthorService service;

    public AuthorController(AuthorService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO author){
        Author authorEntity =author.mapToAuthor();
        service.save(authorEntity);

      URI location= ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").buildAndExpand(authorEntity.getId()).
                toUri();

        return  ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> detailsByID(@PathVariable String id){
        var idAuthor=UUID.fromString(id);
       Optional<Author> author=service.findById(idAuthor);
       if (author.isPresent()){
           Author entity=author.get();
           AuthorDTO dto=new AuthorDTO(entity.getId(),entity.getName(),entity.getDateBirth(),entity.getNationality());
           return ResponseEntity.ok(dto);
       }
        return ResponseEntity.notFound().build();
    }
}
