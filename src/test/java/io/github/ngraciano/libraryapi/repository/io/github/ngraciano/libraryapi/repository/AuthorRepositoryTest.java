package io.github.ngraciano.libraryapi.repository;

import io.github.ngraciano.libraryapi.model.Author;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Transactional
@SpringBootTest
public class AuthorRepositoryTest {
//
//    @Autowired
//    AuthorRepository repo;
//
//    @Test
//    public void countTest(){
//        System.out.println("Contagem"+repo.count());
//    }
//
//    @Test
//    public void listTest(){
//        List<Author> lista=repo.findAll();
//        lista.forEach(System.out::println);
//    }
}
