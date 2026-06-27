package io.github.ngraciano.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="author")
@Data
public class Author {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100,nullable = false)
    private String name;

    @Column(name="date_birth",nullable = false)
    private LocalDate dateBirth;

    @Column(name = "nationality",length = 50)
    private String nationality;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
