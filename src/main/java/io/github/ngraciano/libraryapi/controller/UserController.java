package io.github.ngraciano.libraryapi.controller;


import io.github.ngraciano.libraryapi.controller.dto.UserDTO;
import io.github.ngraciano.libraryapi.controller.mappers.UserMapper;
import io.github.ngraciano.libraryapi.model.User;
import io.github.ngraciano.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDTO dto){
        User user=mapper.toEntity(dto);
        service.save(user);
    }
}
