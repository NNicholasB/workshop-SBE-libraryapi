package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.AuthorDTO;
import io.github.ngraciano.libraryapi.controller.dto.ErrorResponse;
import io.github.ngraciano.libraryapi.model.Author;
import io.github.ngraciano.libraryapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        UUID idAuthor=UUID.fromString(id);
       Optional<Author> author=service.findById(idAuthor);
       if (author.isPresent()){
           Author entity=author.get();
           AuthorDTO dto=new AuthorDTO(entity.getId(),entity.getName(),entity.getDateBirth(),entity.getNationality());
           return ResponseEntity.ok(dto);
       }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        UUID idAuthor=UUID.fromString(id);
        Optional<Author> author=service.findById(idAuthor);
        if (author.isEmpty()){
            return ResponseEntity.notFound().build();
                  }
        service.delete(author.get());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(value="name",required = false) String name,@RequestParam(value="nationality",required = false) String nationality){
        List<Author> result= service.search(name,nationality);
        List<AuthorDTO> list=result.stream().map(author -> new AuthorDTO(author.getId(),author.getName(),author.getDateBirth(),author.getNationality())).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id,@RequestBody AuthorDTO dto){
        UUID idAuthor=UUID.fromString(id);
        Optional<Author>authorOp=service.findById(idAuthor);
        if (authorOp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Author author=authorOp.get();
        author.setName(dto.name());
        author.setNationality(dto.nationality());
        author.setDateBirth(dto.dateBirth());
        service.update(author);
        return ResponseEntity.noContent().build();
    }
}
