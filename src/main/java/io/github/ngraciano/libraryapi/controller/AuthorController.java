package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}
