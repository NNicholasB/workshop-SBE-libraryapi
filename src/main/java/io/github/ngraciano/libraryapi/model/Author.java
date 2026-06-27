package io.github.ngraciano.libraryapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="author")
@Getter
@Setter
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

}
